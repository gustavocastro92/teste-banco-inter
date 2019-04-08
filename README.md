# banco-inter-gustavo-castro

# Aplicação desenvolvida por Gustavo G. Castro (gustavogcastro1992@gmail.com)

#Realizado:

	- [x] Contruir aplicação
	- [ ] Construir testes

## Para executar a aplicação:

Executar o comando "mvnw spring-boot:run" (sem áspas) na raíz do diretório onde o projeto foi clonado

## Para compilar a aplicação em um arquivo jar:

1. Executar o comando "mvnw clean package" (sem áspas) na raíz do diretório onde o projeto foi clonado
2. Executar o comando "java -jar ./target/testebancointer-0.0.1-SNAPSHOT.jar" (sem áspas e em um ambiente Windows) na raíz do diretório onde o projeto foi clonado

## Exemplos de chamadas:

## Jobs

### 1. PUT para Jobs

	PUT:  /jobs/{id} ex.:  /jobs/1
    Descrição: Insere na memória os jobs informados no JSON
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


### 2. GET:  /jobs
    Parâmetro: sortByWeight (opcional)
    Exemplo:  /jobs/sortByWeight=true
	Descrição: Retorna todos os jobs, podendo ser ordenado de acordo com o parâmetro 'sortByWeight'
    
### 3. POST: /jobs
    Descrição: Insere na memória os jobs informados no JSON
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
### 4. GET:  /jobs/{id}
    Parâmetro: sortByWeight (opcional)
    Exemplo:  /jobs/1
    Descrição: Retorna o job com o id informado na URL
    
### 5. DELETE:  /jobs/{id}
    Exemplo:  /jobs/1
    Descrição: Deleta o job com o id informado na URL

## Tasks
### 1. GET:  /tasks
    Parâmetro: createdAt (opcional)(formato: "yyyy-MM-dd")
    Descrição: Retorna todas as tasks, podendo ser filtrada por data
### 2. POST: /tasks
    Descrição: Insere uma task a partir de um JSON
    Corpo da requisição: JSON no formato de tasks
```json
    {
      "id": 2,
      "name": "Second task",
      "weight": 5,
      "completed": false,
      "createdAt": "2017-05-23"
    }
```
### 3. GET: /tasks/{id}
    Exemplo: /tasks/1
    Descrição: Retorna um JSON referente à task com o id informado na URL

### 4. PUT: /tasks/{id}
    Descrição: Insere uma task a partir de um JSON
    Corpo da requisição: JSON no formato de tasks
```json
    {
      "id": 2,
      "name": "Second task",
      "weight": 5,
      "completed": false,
      "createdAt": "2017-05-23"
    }
```

### 5. DELETE: /tasks/{id}
    Exemplo: /tasks/1
    Descrição: Deleta a task referente ao ID da URL
    
## Testes
    Os testes podem ser executado com o comando "mvn test" (sem áspas) executado no diretório que o projeto foi clonado.
    Os testes estão pendentes de término. 
