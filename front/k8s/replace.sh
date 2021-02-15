#!/usr/bin/env bash

kubectl replace -f frontend.deployment.yml --force
kubectl replace -f frontend.service.yml --force
