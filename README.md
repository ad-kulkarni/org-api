# org-api
Organization API

# About
This project is a RESTful API application built on Java SpringBoot that allows users to perform operations on Organizations and Users.
Following operations are supported as of now,
- Create a single Organization
- Create a single User
- Add a User to an Organization
- Delete a User from an Organization
- Read all Users who belong to specific Organization
- Read all Organizations to which a user belongs.

# Technologies used
- Java 8
- SpringBoot 2.2
- Swagger UI 2.7
- H2 database

# Build tools used
- Gradle 4.10 (Gradle 4+ recommended)

# Building/Running the project
- Option 1: Using Gradle<br/>
  After cloning the project and installing Gradle, use a command line tool and navigate to the root directory of the project.
  Use the following command to run the application,<br/><br/>
  ``` gradle bootRun ```
  <br/><br/>
  Once the application is running successfully, navigate to Swagger UI - http://localhost:8080/swagger-ui.html#
  <br/>
  Swagger UI is an interactive API visualization tool.
  

- Option 2: Using Docker image<br/>
  Build a docker image of the application using the following command, <br/><br/>
  ``` gradle buildDocker ```
  <br><br/>
  Once the image is ready, you can run it in a container using the following command, <br/><br/>
  ``` docker run org-api ```
  <br><br/>
