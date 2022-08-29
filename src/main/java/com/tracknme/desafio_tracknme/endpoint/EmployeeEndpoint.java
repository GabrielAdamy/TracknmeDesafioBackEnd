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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeEndpoint {

    @Autowired
    private EmployeeService service;

    @GetMapping(path = "/{id}")
    @Cacheable("id")
    public Employee getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(path = "/cep/{cep}")
    @Cacheable("cep")
    public Page<Employee> findByCep(@PathVariable String cep, Pageable pageable) {
        return service.findByCep(cep, pageable);
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

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Employee> updateE(@RequestBody @Valid Employee employee) {
        Employee resource = service.update(employee);
        return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid Map<String, Object> response) {
        service.updatePatch(id, response);
    }
}
