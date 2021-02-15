#!/usr/bin/env bash

docker build -t gcr.io/mesmerizing-app-280416/kafka-proxy .
docker push gcr.io/mesmerizing-app-280416/kafka-proxy

cd k8s
./deploy.sh
cd ..
