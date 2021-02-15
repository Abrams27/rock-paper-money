#!/usr/bin/env bash

kubectl replace -f zookeeper.deployment.yml --force
kubectl replace -f zookeeper.service.yml --force

kubectl replace -f kafka.service.yml --force
kubectl replace -f kafka.deployment.yml --force
