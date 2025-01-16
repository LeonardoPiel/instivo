# Instivo App - Execução com Docker

Este projeto é uma aplicação Spring Boot que se conecta a um banco de dados MongoDB. Neste guia, você aprenderá como executar a aplicação e o banco de dados MongoDB utilizando o Docker.

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

## Passo a Passo

### 1. Clonar o Repositório

Primeiro, clone o repositório do projeto para sua máquina local:

```bash
cd instivotesting
```


### 2. Construir as Imagens e Subir os Containers
Com o repositório clonado e o Docker instalado, siga as etapas abaixo:

Construir as imagens e subir os containers:
```bash 
docker-compose up --build -d
```
O comando acima fará o Docker Compose construir as imagens do projeto e iniciar os containers em segundo plano.

### 3. Verificar se os Containers Estão Rodando
Após os containers serem iniciados, você pode verificar se estão em execução com o comando:

```bash
docker-compose ps
```
Isso exibirá os containers ativos, incluindo o MongoDB e a aplicação Spring Boot.

### 4. Acessar a Aplicação
Sua aplicação estará rodando na porta 8085 do seu host local. Você pode acessá-la via navegador, utilizando o endereço:

http://localhost:8085

### 5. Acessar o MongoDB
O MongoDB estará disponível na porta 27017 no seu host local. Você pode conectar a ele utilizando o cliente MongoDB de sua preferência, como o MongoDB Compass ou a linha de comando, utilizando a seguinte URI de conexão:

mongodb://admin:Instivo123#@localhost:27017/instivotesting_db?authSource=admin
### 6. Parar os Containers
Quando terminar de usar a aplicação, você pode parar os containers com o seguinte comando:


```bash
docker-compose down
```

Isso irá parar e remover os containers, mas os dados do MongoDB serão preservados graças ao volume definido no docker-compose.yml.

###### 7. Informações Adicionais
MongoDB: A senha de acesso ao MongoDB foi definida como Instivo123# no arquivo docker-compose.yml.
Spring Boot: A aplicação Spring Boot utiliza a variável de ambiente SPRING_DATA_MONGODB_URI para se conectar ao MongoDB.






