#!/usr/bin/env bash

kubectl apply -f zookeeper.deployment.yml
kubectl apply -f zookeeper.service.yml

kubectl apply -f kafka.service.yml
kubectl apply -f kafka.deployment.yml
