#!/usr/bin/env bash

kubectl replace -f gateway.deployment.yml --force
kubectl replace -f gateway.service.yml --force
