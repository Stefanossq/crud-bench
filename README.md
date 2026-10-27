# CRUD Bench

Trabalho 1 - ELC1037 Sistemas de Informacao Distribuidos (UFSM)
Autor: Stefano Camargo Squarcieri
Professor(a): Marcia Pasin

## Sobre

Aplicacao CRUD de clientes com avaliacao de desempenho em 3 cenarios
de carga (A/B/C), variando proporcao leitura/escrita, tamanho de
dataset e presenca de indices. Comparacao SQL vs NoSQL teorica.

Relatorio: docs/relatorio.pdf

## Stack

- API: Spring Boot 3.2.5 + Java 17
- Banco: PostgreSQL 16
- Carga: Apache JMeter 5.6.3
- Infra: Docker Compose
- Docs: Swagger UI + Prometheus
- Dataset: Python Faker (50k registros)

## Como rodar

### 1. Subir o banco

    docker compose up -d
    docker ps

Aguarde crudbench-pg aparecer como (healthy).

### 2. Popular o dataset

    docker exec -i crudbench-pg psql -U admin -d crudbench < db/schema.sql
    docker exec -i crudbench-pg psql -U admin -d crudbench < db/seed_50k.sql
    docker exec -it crudbench-pg psql -U admin -d crudbench -c "SELECT COUNT(*) FROM clientes;"

Deve retornar 50000.

### 3. Rodar a API

    cd app
    mvn clean package -DskipTests
    mvn spring-boot:run

API em http://localhost:8080. Swagger em /swagger-ui/index.html.

### 4. Rodar cenarios JMeter

    jmeter -n -t jmeter/cenario_A_50_50.jmx -l resultados/A.jtl -e -o relatorios/A
    jmeter -n -t jmeter/cenario_B_75_25.jmx -l resultados/B.jtl -e -o relatorios/B
    jmeter -n -t jmeter/cenario_C_25_75.jmx -l resultados/C.jtl -e -o relatorios/C

## API

- POST /api/clientes - criar
- GET /api/clientes/{id} - buscar por ID
- GET /api/clientes?page=0&size=20 - listar paginado
- PUT /api/clientes/{id} - atualizar
- DELETE /api/clientes/{id} - remover
- GET /api/clientes/search?q=nome - buscar por nome

Health: http://localhost:8080/actuator/health
Prometheus: http://localhost:8080/actuator/prometheus

## Resultados (50k registros)

Cenario A 50/50: 3363 req/s sem indice, 3045 com indice
Cenario B 75/25: 3757 req/s sem indice, 3726 com indice
Cenario C 25/75: 3069 req/s sem indice, 2873 com indice

## Resultados (20k registros, com indice)

Cenario A: 3253 req/s
Cenario B: 3773 req/s
Cenario C: 2472 req/s

## Indice GIN trigram

Consulta: SELECT * FROM clientes WHERE lower(nome) LIKE lower('%silva%')

Sem trigram: 56.1 ms (Parallel Seq Scan)
Com GIN trigram: 1.4 ms (Bitmap Index Scan)
Ganho: 40x mais rapido.

## Conclusao

- Mais leitura = mais throughput (B > A > C)
- Indices B-tree so ajudam quando a workload consulta as colunas
- Busca LIKE '%x%' requer GIN + pg_trgm, nao B-tree
- SQL vs NoSQL depende dos requisitos

## Limitacoes


https://github.com/user-attachments/assets/ecbde160-4b43-408b-8fec-1e7a9660d422


- Cada cenario rodou 1 vez (sem intervalo de confianca)
- Dataset cresce durante os testes (usar reset antes)
- Comparacao SQL/NoSQL e teorica
- Frontend: apenas Swagger UI


DOCKER UP : 



https://github.com/user-attachments/assets/15a6b6dc-3f56-4f3b-a03d-0b380cc3b760


