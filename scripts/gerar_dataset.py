import random
from faker import Faker

fake = Faker('pt_BR')
random.seed(42)

TOTAL = 50_000
OUT = 'db/seed_50k.sql'

with open(OUT, 'w', encoding='utf-8') as f:
    f.write("TRUNCATE clientes RESTART IDENTITY;\n")
    for i in range(TOTAL):
        nome = fake.name().replace("'", "''")
        email = fake.unique.email()
        tel = fake.phone_number().replace("'", "''")
        cidade = fake.city().replace("'", "''")
        uf = fake.estado_sigla()
        f.write(
            f"INSERT INTO clientes (nome,email,telefone,cidade,estado) "
            f"VALUES ('{nome}','{email}','{tel}','{cidade}','{uf}');\n"
        )
        if (i + 1) % 5000 == 0:
            print(f"  {i+1} registros gerados...")

print(f"OK! Arquivo {OUT} pronto com {TOTAL} registros.")