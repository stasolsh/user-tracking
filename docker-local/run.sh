#!/usr/bin/env bash

cp -r ./docker-compose.yml ../../

ORIGIN=$(pwd)
cd ../../
ROOT=$(pwd)

docker-compose -p user-tracking -f docker-compose.yml stop user-tracking

cd "$ROOT"/user-tracking
mvn -T 1C clean install -DskipTests=true

cd "$ROOT"/
docker-compose -p user-tracking -f docker-compose.yml up --build -d mongodb mongodb-management --no-deps user-tracking

cd "$ORIGIN"