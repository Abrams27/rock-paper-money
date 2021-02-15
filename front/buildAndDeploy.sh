#!/usr/bin/env bash

docker build -t gcr.io/mesmerizing-app-280416/frontend .
docker push gcr.io/mesmerizing-app-280416/frontend

cd k8s
./deploy.sh
cd ..
