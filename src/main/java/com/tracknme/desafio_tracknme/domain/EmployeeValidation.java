package com.tracknme.desafio_tracknme.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeValidation {

    public static boolean isValid(Employee fun){
        if (fun.getName() == null || fun.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Informe o nome do Funcionario.");
        }
        if (fun.getAge() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe a idade do Funcionario.");
        }
        if (fun.getCep() == null || fun.getCep().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Informe o Cep do Funcionario.");
        }
        return true;
    }
}

