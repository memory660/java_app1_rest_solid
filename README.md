docker compose up -d

POST http://localhost:8080/api/employees

{
"firstname": "firstname1",
"lastname": "lastname1",
"email": "email1@email.fr"
}

GET http://localhost:8080/api/employees/x