# User Tracking Service
![Build](https://github.com/stasolsh/user-tracking/actions/workflows/custom-action.yml/badge.svg)
![Coverage](https://codecov.io/gh/stasolsh/user-tracking/branch/master/graph/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9+-blue)
![JUnit](https://img.shields.io/badge/JUnit-5-red?logo=junit5)
![License](https://img.shields.io/badge/license-MIT-green)

## Table of Contents:

🎯 [Objective](#-objective)  
🏃 [Running the project](#-running-the-project)  
📚 [API Documentation](#-api-documentation)  
🔍 [Visualizing Data](#-visualizing-data)

---
## 🎯 Objective

The microservice is responsible for storing and processing user actions. This microservice has APIs that are both imperative and reactive. Data persists in MongoDB.

## 🏃 Running the project

You should have a **Docker** environment with support to **Docker Compose**.

> ⚠️ _This project uses batch and bash scripts to make some commands easier to run._

Open your terminal in the root folder and type:

```batch
 cd docker-local
 run.bat
```

```bash
./docker-local/run.sh
```

This script will build a .jar artifact, build your images, and launch all containers.

To stop running containers, just type:

```batch
cd docker-local
stop.bat
```

```bash
./docker-local/stop.sh
```

and all your containers will be dropped and volumes will be removed.

## 📚 API Documentation

All endpoints were documented using Swagger 3 for Spring Boot 3. All you have to do is open [`http://localhost:8000/docs`](http://localhost:8000/docs) and give it a go.

## 🔍 Visualizing Data

MongoDB's service are not exposed at any port to the host machine, so you cannot connect directly to them. Please, use

- _MongoDB Database Manager_ available at [`http://localhost:8081/`](http://localhost:8081/)
    - **User**: `admin`
    - **Password**: `pass`


