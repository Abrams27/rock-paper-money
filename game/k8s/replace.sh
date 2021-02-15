#!/usr/bin/env bash

kubectl replace -f game.deployment.yml --force
kubectl replace -f game.service.yml --force
