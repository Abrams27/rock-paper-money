#!/usr/bin/env bash

kubectl replace -f user.deployment.yml --force
kubectl replace -f user.service.yml --force
