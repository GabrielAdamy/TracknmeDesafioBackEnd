# TracknmeDesafioBackEnd

# Projeto com intuito de testar a a evolução do desenvolvedor jr na empresa Tracknme

# ENDPOINTS 
POST
- http://localhost:8080/employee/create
Body: {
   "name": String, (OBRIGATÓRIO)
   "age": int, (OBRIGATÓRIO)
   "sex": String, (OBRIGATÓRIO)
   "cep": String (OBRIGATÓRIO)
}

GET
- http://localhost:8080/employee

GET
- http://localhost:8080/employee/cep/{cep String}

GET
- http://localhost:8080/viacep/cep/93110270

DELETE
- http://localhost:8080/employee/{id}

PUT
- http://localhost:8080/employee/update/{id}
Body: {
            "name": String, (OBRIGATÓRIO)
            "age": int, (OBRIGATÓRIO)
            "sex": String,
            "cep": String, (OBRIGATÓRIO)
            "address": String,
            "district": String,
            "city": String,
            "state": String
}

PATCH
- http://localhost:8080/employee/{id}
Body: {
            "name": String, (OBRIGATÓRIO)
            "age": int, (OBRIGATÓRIO)
            "sex": String,
            "cep": String (OBRIGATÓRIO)
}
