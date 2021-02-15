#!/usr/bin/env bash

kubectl replace -f matchmaking.deployment.yml --force
kubectl replace -f matchmaking.service.yml --force
