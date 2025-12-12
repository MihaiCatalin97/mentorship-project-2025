# Local Deployment via Rancher Desktop and Docker

## Overview
- This module provides scripts and Maven profiles to deploy all microservices locally using Docker Compose.
- The setup allows developers to build Docker images for the entire system and run all services simultaneously for local testing and validation.

## Prerequisites
- Install Rancher Desktop with Docker support enabled.
  - Ensure the Docker CLI is accessible from the terminal by running:
    ```bash
    docker --version
    ```
  -Ensure that a bash-compatible shell is available.
  On Windows, this requires using WSL, and WSL integration must be enabled for your Linux distribution in Rancher Desktop settings.
  ![RancherSettings.png](docs/images/RancherSettings.png)
  ![Terminal.png](docs/images/Terminal.png)

## Build Services
- Navigate to the root directory of the project.
- Run the following Maven commands to deploy locally through Docker:
  1. Build Docker images for all services:
  ```bash
  mvn clean install -Pdocker
  ```
  2. Deploy all services locally using Docker Compose:
    ```bash 
    mvn install -Pdeploy-local -pl deploy
    ```

## Deployment Workflow
   1. Activate the docker profile → Maven builds all microservice images using Jib and publishes them to the local Docker environment.
   2. Activate the deploy-local profile → Maven uses Docker Compose to start all services defined in the `docker-compose.yml` file located in the `deploy/local` directory.
   3. All services will be accessible at their respective endpoints as defined in the Docker Compose configuration.
