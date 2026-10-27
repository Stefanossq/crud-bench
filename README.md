# CRUD Bench — Avaliação de Desempenho de Aplicação CRUD

**Trabalho 1 — ELC1037 Sistemas de Informação Distribuídos (UFSM)**  
**Autor:** Stéfano Camargo Squarcieri  
**Professor(a):** Marcia Pasin

---

## Sobre

Aplicação CRUD de clientes com avaliação de desempenho em 3 cenários
de carga (A/B/C), variando proporção leitura/escrita, tamanho de
dataset e presença de índices. Comparação SQL vs NoSQL teórica.

**📄 Relatório:** [PDF](docs/relatorio.pdf)  
**🎥 Vídeo demo:** [YouTube](LINK_VIDEO) *(adicionar após gravar)*

---

## Stack

| Componente | Tecnologia |
|------------|-----------|
| API | Spring Boot 3.2.5 + Java 17 |
| Banco | PostgreSQL 16 |
| Carga | Apache JMeter 5.6.3 |
| Infra | Docker Compose |
| Docs | Swagger UI + Prometheus |
| Dataset | Python Faker (50k registros) |

---

## Como rodar

### 1. Subir o banco

```bash
docker compose up -d
docker ps   # aguardar crudbench-pg (healthy)