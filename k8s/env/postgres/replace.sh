#!/usr/bin/env bash

kubectl replace -f postgres.configmap.yml --force
kubectl replace -f postgres.storage.yml --force
kubectl replace -f postgres.deployment.yml --force
kubectl replace -f postgres.service.yml --force
