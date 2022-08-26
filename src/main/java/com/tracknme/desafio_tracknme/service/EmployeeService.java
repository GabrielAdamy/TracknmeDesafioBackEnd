package com.tracknme.desafio_tracknme.service;

import com.tracknme.desafio_tracknme.client.ViaCepClient;
import com.tracknme.desafio_tracknme.domain.Employee;
import com.tracknme.desafio_tracknme.domain.ViaCepDTO;
import com.tracknme.desafio_tracknme.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private ViaCepClient client;

    @Autowired
    private EmployeeRepository repository;

    public Employee create(Employee fun) throws Exception {
        Employee employee = new Employee();
        if (fun.isValid()) {
            ResponseEntity<ViaCepDTO> cepDTO = client.cep(fun.getCep());
            employee.setName(fun.getName());
            employee.setAge(fun.getAge());
            employee.setSex(fun.getSex());
            employee.setCep(fun.getCep());
            employee.setCity(cepDTO.getBody().getLocalidade());
            employee.setDistrict(cepDTO.getBody().getBairro());
            employee.setAddress(cepDTO.getBody().getLogradouro());
            employee.setState(cepDTO.getBody().getUf());
        }
        return repository.save(employee);
    }

    public Employee update(Long id, Employee fun) {
        Employee employee = repository.getById(id);

        if (fun.getCep() != null) {
            ResponseEntity<ViaCepDTO> cepDTO = findCep(fun.getCep());
            employee.setCep(cepDTO.getBody().getCep());
            employee.setAddress(cepDTO.getBody().getLogradouro());
            employee.setCity(cepDTO.getBody().getLocalidade());
            employee.setDistrict(cepDTO.getBody().getBairro());
            employee.setState(cepDTO.getBody().getUf());
        }
        if (fun.getName() != null) {
            employee.setName(fun.getName());
        }
        if (fun.getAge() != null) {
            employee.setAge(fun.getAge());
        }
        if (fun.getSex() != null) {
            employee.setSex(fun.getSex());
        }
        return repository.save(employee);
    }

    public Employee update(Employee fun) {
        return repository.save(fun);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Employee> findByCep(String cep) {
        return repository.findByCep(cep);
    }

    public ResponseEntity<ViaCepDTO> findCep(String cep) {
        return client.cep(cep);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

}
