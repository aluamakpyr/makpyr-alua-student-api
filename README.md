# makpyr-alua-student-api

Student: Alua Makpyr
Group: IT2-2306
Student ID: 37566
Course: DevOps - Midterm

Spring Boot CRUD REST API for students with PostgreSQL, Docker, Docker Compose and Jenkins.

Status: stable version on main

## Changes by Alua Makpyr (feature/makpyr-alua-crud)

- Added UPDATE operation: `PUT /makpyr-alua/api/students/{id}`
- Requests for a nonexistent student now return HTTP 404 with a JSON error message
- Added unit tests for the update operation and the 404 case
