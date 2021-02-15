#!/usr/bin/env bash

kubectl replace -f kafka-proxy.deployment.yml --force
kubectl replace -f kafka-proxy.service.yml --force
