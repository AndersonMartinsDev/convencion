
# Projeto Pautas
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)  ![Static Badge](https://img.shields.io/badge/autor-Anderson-blue)
![Static Badge](https://img.shields.io/badge/stack-java17-blue)  ![Static Badge](https://img.shields.io/badge/framework-spring-green)


        O Convence é uma api completa para gestão de pautas e votações, projetado para facilitar 
    o processo decisório em equipes e organizações. Construído com Java 17 e Spring Framework,o 
    Convence oferece recursos robustos e uma arquitetura robusta baseada em Onion Architecture.


## Documentation



#### Gerenciamento de Pauta:

Crie e edite pautas com detalhes completos
Organize as pautas
Utilize a paginação para navegar facilmente por um grande número de pautas.

#### Gerenciamento de Membros:

Cadastre e gerencie membros.
Atribua membros às pautas para garantir a participação adequada nas decisões.
Visualize a lista de membros por pauta e acompanhe os votos.

#### Funcionalidade de Votação:

Realize votações online seguras e confiáveis para cada item da pauta.
Permita que os membros votem "sim", "não" em cada item.
Visualize os resultados das votações em tempo real.

## Tech Stack



**Linguagem de Programação:** Java 17

**Framework Web:** Spring Framework

**Arquitetura:** Onion Architecture

**Banco de Dados:** MYSQL



## Installation

Pré-requisitos:
```bash
    Java 17 instalado
    Maven instalado
    Banco de dados configurado e acessível
    Etapas de Instalação:

    Clone o repositório do projeto.
    Instale as dependências do projeto usando o Maven.
    Configure o banco de dados nas propriedades do projeto.
    Execute a aplicação Spring Boot.
```


| Variables | Type     | Description                                                      |
| :-------- | :------- |:-----------------------------------------------------------------|
| `DATABASE_DDL` | `string` | create, update, none                                             |
| `DATABASE_DRIVER` | `string` | your database driver                                             |
| `DATABASE_USER` | `string` | your database user                                               |
| `DATABASE_PASSWORD` | `string` | your database password                                           |
| `DATABASE_PLATFORM` | `string` | your database platform Ex:org.hibernate.dialect.MySQLDialect     |
| `DATABASE_URL` | `string` | your database host url Ex:jdbc:mysql://127.0.0.1:3306/conference |
| `DATABASE_SQL_SHOW` | `string` | set 'true' to show queries in your terminal log (true or false)  |
| `EXTERNAL_API_URL` | `string` | your external host url                                           |