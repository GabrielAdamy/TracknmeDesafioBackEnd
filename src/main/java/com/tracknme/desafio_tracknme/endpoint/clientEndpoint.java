package com.tracknme.desafio_tracknme.endpoint;

import com.tracknme.desafio_tracknme.domain.ViaCepDTO;
import com.tracknme.desafio_tracknme.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viacep")
public class clientEndpoint {

    @Autowired
    private EmployeeService service;

    @GetMapping(path = "/cep/{cep}")
    @Cacheable("cep")
    public ViaCepDTO findByCep(@PathVariable String cep) {
        return service.findCep(cep);
    }

}
