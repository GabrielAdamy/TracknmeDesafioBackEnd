package com.tracknme.desafio_tracknme.endpoint;

import com.tracknme.desafio_tracknme.domain.Employee;
import com.tracknme.desafio_tracknme.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/employee")
public class EmployeeEndpoint {

    @Autowired
    private EmployeeService service;

    @GetMapping(path = "/{id}")
    @Cacheable("id")
    public Optional<Employee> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(path = "/cep/{cep}")
    @Cacheable("cep")
    public List<Employee> findByCep(@PathVariable String cep) {
        return service.findByCep(cep);
    }

    @GetMapping
    public Page<Employee> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping(path = "/create")
    public Employee create(@RequestBody @Validated Employee employee) throws Exception {
        return service.create(employee);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Employee> update(@RequestBody @Valid Employee employee) {
        Employee resource = service.update(employee);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody @Valid Employee employee) {
        Employee resource = service.update(id, employee);
       return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
