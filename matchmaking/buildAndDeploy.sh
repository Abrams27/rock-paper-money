#!/usr/bin/env bash

docker build -t gcr.io/mesmerizing-app-280416/matchmaking .
docker push gcr.io/mesmerizing-app-280416/matchmaking

cd k8s
./deploy.sh
cd ..
