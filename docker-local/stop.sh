#!/usr/bin/env bash

ORIGIN=$(pwd)
cd ../../

docker-compose -p user-tracking -f docker-compose.yml down
cd "$ORIGIN"