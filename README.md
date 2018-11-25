# banco-inter-gustavo-castro

# Aplicação desenvolvida por Gustavo G. Castro (gustavogcastro1992@gmail.com)

# Realizado:
- [x] Contruir aplicação
- [ ] Construir testes

## Para executar a aplicação:

Executar o comando "mvnw spring-boot:run" (sem áspas) na raíz do diretório onde o projeto foi clonado

## Para compilar a aplicação em um arquivo jar:

1. Executar o comando "mvnw clean package" (sem áspas) na raíz do diretório onde o projeto foi clonado
2. Executar o comando "java -jar ./target/testebancointer-0.0.1-SNAPSHOT.jar" (sem áspas e em um ambiente Windows) na raíz do diretório onde o projeto foi clonado

## Exemplos de chamadas:
### 1. PUT para Jobs

PUT: http://localhost:8080/jobs/{id}

ex.:

	http://localhost:8080/jobs/1
	
Corpo da requisição: JSON com os jobs conforme exemplo abaixo

```json
{
  "id": 1,
  "name": "Job1",
  "active": true,
  "parentJob": {
    "id": 2,
    "name": "Second job",
    "active": true
  },
  "tasks": [
    {
      "id": 1,
      "name": "First task",
      "weight": 5,
      "completed": true,
      "createdAt": "2015-05-23"
    },
    {
      "id": 2,
      "name": "Second task",
      "weight": 2,
      "completed": false,
      "createdAt": "2017-05-23"
    }
  ]
}
```


### 2. GET:  http://localhost:8080/jobs
	Resposta: Todos os jobs 

