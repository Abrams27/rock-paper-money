#!/usr/bin/env bash

docker build -t gcr.io/mesmerizing-app-280416/user .
docker push gcr.io/mesmerizing-app-280416/user

cd k8s
./deploy.sh
cd ..
