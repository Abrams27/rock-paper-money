#!/usr/bin/env bash

docker build -t gcr.io/mesmerizing-app-280416/game .
docker push gcr.io/mesmerizing-app-280416/game

cd k8s
./deploy.sh
cd ..
