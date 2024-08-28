### Sistema de Replicação de Banco de Dados

Este projeto é uma aplicação para replicação ativa de banco de dados utilizando comunicação em grupo. A aplicação é projetada para distribuir comandos SQL entre uma instância líder e suas réplicas, garantindo a consistência dos dados entre as réplicas.
Estrutura do Projeto

    Líder: Recebe comandos SQL e os distribui para as réplicas.
    Réplicas: Recebem e executam os comandos SQL distribuídos pela instância líder.

#### Tecnologias Utilizadas

    Java: Linguagem de programação principal.
    Spring Boot: Framework para desenvolvimento da aplicação.
    RabbitMQ: Sistema de mensagens para comunicação entre instâncias.
    Docker: Para containerização dos serviços.
    PostgreSQL: Banco de dados relacional utilizado.


#### Endpoints da API
##### Enviar Comando SQL

    URL: /command

    Método: POST

    Corpo da Requisição:

    json

    {
      "command": "SEU_COMANDO_SQL"
    }

#### Resposta:

    Status HTTP 201 (Criado): Se o comando foi executado com sucesso.
    Status HTTP 500 (Erro Interno do Servidor): Se houve uma falha ao executar o comando.

#### Configuração do Ambiente

#### Clone o repositório

bash

git clone https://github.com/ViniciusSantos19/ReplicacaoDistribuidaProject.git
cd sistema-replicacao

#### Configuração do Docker Compose

O projeto utiliza Docker Compose para orquestrar os serviços. Certifique-se de ter o Docker e o Docker Compose instalados. Crie um arquivo .env com as variáveis necessárias:

#### env

   
    POSTGRES_USER=vinicius
    POSTGRES_PASSWORD=1234
    POSTGRES_DB=si_database
    POSTGRES_URL=jdbc:postgresql://db_si:5432/$POSTGRES_DB

    RABBIT_USER=guest
    RABBIT_PASSWORD=guest
    HOST_RABBIT=rabbitmq
    PORT_RABBIT=5672

    QUEUE_NAME=instancia-1
    NOTFY_QUEUE=notfy-instancia-1


#### Inicie os serviços com:

bash

docker-compose up --build

Configuração da Aplicação

Configure os parâmetros de conexão ao RabbitMQ e ao PostgreSQL no arquivo application.properties do Spring Boot. Exemplo:

#### properties

    spring.datasource.url=jdbc:postgresql://db:5432/sua_base
    spring.datasource.username=seuusuario
    spring.datasource.password=suasenha

    spring.rabbitmq.host=amqp
    spring.rabbitmq.username=usuario
    spring.rabbitmq.password=senha
    instance.id=${INSTANCE_ID}
    instance.lider=${LIDER_ID}
    instance.queue.name=${QUEUE_NAME}
    instance.queue.notfy=${NOTFY_QUEUE}
    server.port=${PORTA:8080}

#### Executando a Aplicação

Após iniciar os serviços com Docker Compose, a aplicação estará disponível em:

    Líder: http://localhost:8080
    Réplicas: Cada réplica estará acessível em portas diferentes, dependendo da configuração.

#### Testes

Para testar a replicação:

    Envie um comando SQL para a instância líder via endpoint REST.
    Verifique se a alteração foi replicada nas réplicas.
    No log das aplicações vai aparecer o comando sql que foi enviado

#### Estrutura do Projeto

    src/main/java: Código fonte principal.
    src/test/java: Testes automatizados.
    docker-compose.yml: Arquivo de configuração do Docker Compose.
    application.properties: Configurações da aplicação Spring Boot.
