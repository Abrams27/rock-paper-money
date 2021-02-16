#!/usr/bin/env bash

kubectl apply -f gateway.deployment.yml
kubectl apply -f gateway.service.yml
