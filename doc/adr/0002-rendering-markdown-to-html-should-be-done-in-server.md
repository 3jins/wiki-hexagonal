# 2. Rendering markdown to HTML should be done in server

Date: 2023-03-19

## Status

Accepted

## Context

Markdown data can be rendered into HTML both in frontend or backend.

## Decision

- Considering SEO, it has an advantage if the backend respond with the completed semantic markup data.
- It's better to keep the frontend bundle file size light.

## Consequences

Backend should take the responsibility to render.
