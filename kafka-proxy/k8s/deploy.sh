#!/usr/bin/env bash

kubectl apply -f kafka-proxy.deployment.yml
kubectl apply -f kafka-proxy.service.yml
