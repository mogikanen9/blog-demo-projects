# Download base image ubuntu18.04
FROM ubuntu:18.04

# Add Maintainer Info
LABEL maintainer="mogikanensoftware@gmail.com"

# Update Ubuntu Software repository
RUN apt-get update

# Install Open JDK
RUN apt-get install -y openjdk-8-jdk

# Install Git
RUN apt-get install -y git

## Install Ansible
RUN apt-get install -y software-properties-common
RUN apt-add-repository -y -u ppa:ansible/ansible
RUN apt-get install -y ansible
