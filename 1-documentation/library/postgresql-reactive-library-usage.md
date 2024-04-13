# PostgreSQL Reactive Library Usage Documentation

## Introduction
The PostgreSQL Reactive library provides support for integrating PostgreSQL databases with Spring Boot applications in a reactive manner. This document outlines the usage of key properties required to configure the connection to a PostgreSQL database using the Reactive Postgres driver.

## Properties Overview
The following properties are essential for configuring the PostgreSQL connection in a Spring Boot application:

- `spring.r2dbc.url`: Specifies the URL used to connect to the PostgreSQL database. It typically includes details such as the hostname, port, and database name.
- `spring.r2dbc.username`: Specifies the username used to authenticate with the PostgreSQL database.
- `spring.r2dbc.password`: Specifies the password used to authenticate with the PostgreSQL database.

## Usage Examples
### application.properties
```properties
# PostgreSQL R2DBC URL
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/database

# Database username
spring.r2dbc.username=postgres

# Database password
spring.r2dbc.password=postgres
