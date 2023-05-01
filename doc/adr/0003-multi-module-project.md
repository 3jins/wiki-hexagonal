# 3. multi-module

Date: 2023-04-02

## Status

Accepted

## Context

Source files for backend and frontend should be separated.
Gradle forces to have `build.gradle` file at the project root.

## Decision

It seems little bit early, but decided to apply multi-module architecture to meet the requirement of Gradle.

## Consequences

Backend and frontend modules are separated clearly, and easy to split backend module into two or more sub modules.
