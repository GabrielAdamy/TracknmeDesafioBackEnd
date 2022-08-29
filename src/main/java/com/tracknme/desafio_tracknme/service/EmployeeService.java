package com.tracknme.desafio_tracknme.service;

import com.tracknme.desafio_tracknme.client.ViaCepClient;
import com.tracknme.desafio_tracknme.domain.Employee;
import com.tracknme.desafio_tracknme.domain.ViaCepDTO;
import com.tracknme.desafio_tracknme.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private ViaCepClient client;

    @Autowired
    private EmployeeRepository repository;

    //CREATE
    public Employee create(Employee fun) throws Exception {
        if (fun.isValid()) {
            updateCep(fun);
        }
        return repository.save(fun);
    }

//    //UPDATE A MODA LOKA DESCONSIDERAR
//    public void update(Long id, Employee fun) {
//        Employee employee = findById(id);
//
//        if (fun.getCep() != null) {
//            ViaCepDTO cepDTO = findCep(fun.getCep());
//            employee.setCep(cepDTO.getCep());
//            employee.setAddress(cepDTO.getLogradouro());
//            employee.setCity(cepDTO.getLocalidade());
//            employee.setDistrict(cepDTO.getBairro());
//            employee.setState(cepDTO.getUf());
//        }
//        if (fun.getName() != null) {
//            employee.setName(fun.getName());
//        }
//        if (fun.getAge() != null) {
//            employee.setAge(fun.getAge());
//        }
//        if (fun.getSex() != null) {
//            employee.setSex(fun.getSex());
//        }
//        repository.save(employee);
//    }

    //UPDATE CORRETO E BONITO
    public void updatePatch(Long id, Map<String, Object> parameters) {
        Employee employee = findById(id);
        parameters.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Employee.class, key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, employee, value);

        });
        if (parameters.containsKey("cep")) {
            updateCep(employee);
        }
        update(employee);
    }

    //METODO PARA ATUALIZACAO DO CEP
    private void updateCep(Employee employee) {
        ViaCepDTO cepDTO = findCep(employee.getCep());
        employee.setCep(cepDTO.getCep().replace("-",""));
        employee.setAddress(cepDTO.getLogradouro());
        employee.setCity(cepDTO.getLocalidade());
        employee.setDistrict(cepDTO.getBairro());
        employee.setState(cepDTO.getUf());
    }

    public Employee update(Employee fun) {
        return repository.save(fun);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Employee> findByCep(String cep, Pageable pageable) {
        return repository.findAllByCep(cep, pageable);
    }

    public ViaCepDTO findCep(String cep) {
        return client.cep(cep);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Employee findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
