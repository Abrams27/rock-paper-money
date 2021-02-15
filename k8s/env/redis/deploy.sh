#!/usr/bin/env bash

kubectl apply -f redis-user.deployment.yml
kubectl apply -f redis-user.service.yml

kubectl apply -f redis-game.deployment.yml
kubectl apply -f redis-game.service.yml

