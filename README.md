# Customer Management API

This project is a Customer Management API built with Java, Spring Boot, and Maven. It follows the Hexagonal Architecture pattern to ensure a clean separation of concerns.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Recommended Plugins](#recommended-plugins)
- [Contributing](#contributing)
- [License](#license)

## Architecture

The project is organized following the Hexagonal Architecture (Ports and Adapters) pattern. The main packages are:

- **domain**: Contains the core business logic and domain entities.
- **application**: Contains the application logic, including use cases and DTOs.
- **infrastructure**: Contains the implementation of the output ports, such as repositories and external services.
- **adapter**: Contains the input and output adapters, such as controllers and API clients.

## Endpoints

The API provides the following endpoints:

- `GET /cliente/{id}`: Get customer by ID.
- `GET /cliente/name/{customerName}`: Get customer by name.
- `GET /cliente/documents/{id}`: Get customer documents by ID.
- `GET /cliente/contacts/{id}`: Get customer contacts by ID.
- `POST /cliente/{id}`: Save a new customer.

Refer to the `openapi.yaml` file for detailed API specifications.

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/jeffultra/customer-management-api.git
   cd customer-management-api
   
## Running the Application

This application has a docker-compose file that allows you to run  a MySQL database with a single command.
You need to have docker service installed and running on your machine and then run the following command:
```sh
docker compose up
```

If it is the first time you are running docker compose, it will take some time to download the images and start the 
containers. Also, it will create the database and tables needed for the application to run.

To run the application, use the following command:
```sh
mvn spring-boot:run
```

## Testing

To run the tests, use the following command:
```sh
mvn test
```
### Generate JaCoCo Report

To generate the JaCoCo code coverage report, use the following command:

```sh
mvn jacoco:report
```

The report will be generated in the target/site/jacoco directory. Open the index.html file in a web browser to view the coverage report.

## Recommended Plugins

* Docker
* Github Copilot
* OpenAPI (Swagger) Editor
* SonarQube for IDE