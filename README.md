# User Tracking Service


## Table of Contents:

🎯 [Objective](#-objective)  
🏃 [Running the project](#-running-the-project)  
📄 [Scripts](#-scripts)  
🔍 [Visualizing Data](#-visualizing-data)   
📚 [API Documentation](#-api-documentation)   
🚧 [Troubleshooting](#-troubleshooting)


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

## 📄 Scripts

Beyond `run.bat`/`run.sh` and `stop.bat`/`stop.sh`, we have other helper scripts:

## 🔍 Visualizing Data

MongoDB's service are not exposed at any port to the host machine, so you cannot connect directly to them. Please, use

- _MongoDB Database Manager_ available at [`http://localhost:8081/`](http://localhost:8081/)
    - **User**: `admin`
    - **Password**: `pass`

## 📚 API Documentation

All endpoints were documented using Swagger 3 for Spring Boot 3. All you have to do is open [`http://localhost:8000/docs`](http://localhost:8000/docs) and give it a go.

