## Overview

This project demonstrates how to use LocalStack in conjunction with TestContainers to implement a local AWS S3 environment for integration testing in a Spring Boot application. By leveraging TestContainers, we ensure a consistent and isolated testing environment that can be easily integrated into CI/CD pipelines.

#### Key features of this demo:
- Spring Boot application with AWS S3 integration
- LocalStack setup using TestContainers for simulating S3 locally
- Integration tests showcasing S3 operations in an isolated environment
- Example of how to configure and use TestContainers with LocalStack
- Demonstration of seamless CI/CD integration

## Prerequisites
- Java 17
- Docker

## Key Dependencies

1. Integrates Spring Boot with TestContainers for streamlined testing setup.
```xml
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-testcontainers</artifactId>
            <scope>test</scope>
    </dependency>
```
****
2. Provides JUnit 5 support for TestContainers, enabling easy container lifecycle management in tests.
```xml
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
```
****
3. Official AWS SDK for Java, used for interacting with S3 services.
```xml
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>s3</artifactId>
            <version>2.20.109</version>
        </dependency>
```
****
4. Core TestContainers library, offering a framework for providing throwaway instances of databases, message brokers, and other services.
```xml
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>testcontainers</artifactId>
            <version>1.19.8</version>
            <scope>test</scope>
        </dependency>
```
***
5. TestContainers module for LocalStack, allowing easy setup of a local AWS environment.
```xml
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>localstack</artifactId>
            <version>1.19.8</version>
            <scope>test</scope>
        </dependency>
```


## Running Integration Tests

Follow these steps to run the integration tests:

#### 1. Clone the repository:
`git clone https://github.com/3umarG/AWS-Testing`

#### 2. Ensure you have the following prerequisites installed:
- Java (version specified in your `pom.xml`)
- Maven
- Docker

#### 3. Start Docker:
- On Windows/Mac: Open Docker Desktop
- On Linux: Run `sudo systemctl start docker`

#### 4. Verify Docker is running:
`docker --version`
This should display the Docker version without errors.

#### 5. Run the tests using Maven:
`mvn clean test`
This command will:
- Download necessary dependencies
- Compile the project
- Start TestContainers with LocalStack
- Execute all tests

#### Note: The first run may take longer as TestContainers downloads required Docker images.

If you encounter any issues, ensure:
- All prerequisites are correctly installed
- Docker is running and accessible
- Your system has sufficient resources to run Docker containers




