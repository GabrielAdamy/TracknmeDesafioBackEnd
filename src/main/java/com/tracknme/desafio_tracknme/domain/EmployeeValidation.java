package com.tracknme.desafio_tracknme.domain;

public class EmployeeValidation {

    public static boolean isValid(Employee fun){
        if (fun.getName() == null) {
            throw new Error("Informe o nome do Funcionario.");
        }
        if (fun.getAge() == null) {
            throw new Error("Informe a idade do Funcionario.");
        }
        if (fun.getCep() == null) {
            throw new Error("Informe o Cep do Funcionario.");
        }
        return true;
    }
}

