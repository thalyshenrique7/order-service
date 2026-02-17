# Order Service

Microserviço responsável pela criação de pedidos em uma arquitetura orientada a eventos (Event Driven Architecture).

Ao criar um pedido, o serviço publica um evento `order.created` no RabbitMQ para que outros microserviços (ex: Stock) possam reagir de forma assíncrona.

---

## Tecnologias Utilizadas

- Java 17
- Maven
- Spring Boot
  - Spring Web
  - Spring Data JPA
  - Spring Validation
  - Spring AMQP
- PostgreSQL
- RabbitMQ
- CloudAMQP

---

## Arquitetura

Este serviço segue o padrão **Event Driven Architecture (EDA)**.

### Fluxo
Client → Order Service → Banco de Dados
↓
RabbitMQ (order.created)


### Responsabilidades do Order Service

- Persistir o pedido no banco de dados
- Publicar o evento `order.created`
- Não depender diretamente de outros microserviços

---

## Endpoint

### Criar Pedido
POST /api/order

### Request Body

```json
{
  "items": [
    {
      "productId": 1,
      "quantity": 2,
      "unitPrice": 10.50,
      "totalAmount": 21.00
    }
  ],
  "totalAmount": 21.00
}
```
## Validações

- **productId não pode ser null**

## Evento Publicado

Após a criação do pedido, o serviço publica o seguinte evento:

Exchange
- order.exchange

Routing Key
- order.created

Payload
```
{
  "orderId": 123,
  "items": [...]
}
```
## Configuração RabbitMQ

Exemplo de propriedades:

- broker.exchange.order=order.exchange
- broker.routing.order-created=order.created

O serviço atua como producer, não sendo responsável pela criação das filas dos consumidores.

## Banco de Dados
- PostgreSQL
- Persistência via Spring Data JPA

### Entidades normalizadas:
- Order
- OrderItem

## Comunicação Assíncrona

Este serviço:

- Não chama outros microserviços via HTTP
- Não espera resposta do serviço de estoque
- Apenas publica eventos
- Garante baixo acoplamento entre serviços

## Como Executar
- Subir PostgreSQL
- Subir RabbitMQ (ou utilizar CloudAMQP)
- Configurar application.properties

Executar:
mvn spring-boot:run

> Autor: Thalys Henrique
https://www.linkedin.com/in/thalyshenrique7/

