#!/usr/bin/env bash

kubectl replace -f redis-user.deployment.yml --force
kubectl replace -f redis-user.service.yml --force

kubectl replace -f redis-game.deployment.yml --force
kubectl replace -f redis-game.service.yml --force
