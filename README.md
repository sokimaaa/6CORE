# 6Core

The name "6Core" is a meaningful abbreviation that represents the six core parts of an ecommerce platform.
These six core parts include of management

- cart
- warehouse, product
- order
- user
- shipping
- payment

which are all crucial components of any successful ecommerce business.
The name suggests that this platform is focused on the essential elements
of ecommerce and aims to provide a streamlined and efficient experience
for both businesses and customers. The use of the number "6" in the name
adds a numerical element to the abbreviation, which can help make it
more memorable and distinctive.

## Project Structure

`1-documentation` is place for the platform documentation, architecture decision logs,
flow diagram and et cetera.

`2-library` is considered for placing code sharable between multiple components
and has any business logic. As example of library could be logging configuration.

`3-platform` is core of our project. Here is our business code that utilizes frameworks and libraries
to make our ideas real.

`4-supplement` is folder for storing third-party scripts for CI, local development, testing or any other useful scripts.

## Build libraries

```shell
bash ./4-supplement/traverse-library.sh
```

## Docker resources

Postgres DB:

```
docker run --name postgresDb -e POSTGRES_PASSWORD=postgres -p5432:5432 -d postgres:alpine3.19
```

For connection to DB via IDE use next credentials:

host - `localhost`

port - `5432`

database - `postgres`

URL - `jdbc:postgresql://localhost:5432/postgres`

username - `postgres`

password - `postgres`
