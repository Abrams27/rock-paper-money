#!/usr/bin/env bash

docker build -t gcr.io/mesmerizing-app-280416/gateway .
docker push gcr.io/mesmerizing-app-280416/gateway

./deploy.sh
