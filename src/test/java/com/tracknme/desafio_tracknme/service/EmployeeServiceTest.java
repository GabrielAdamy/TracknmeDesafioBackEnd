package com.tracknme.desafio_tracknme.service;

import com.tracknme.desafio_tracknme.client.ViaCepClient;
import com.tracknme.desafio_tracknme.domain.Employee;
import com.tracknme.desafio_tracknme.domain.ViaCepDTO;
import com.tracknme.desafio_tracknme.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private ViaCepClient client;

    @Mock
    private EmployeeRepository repository;

    private Employee employee = new Employee();
    private ViaCepDTO cep = new ViaCepDTO();

    @Before
    public void setUp() {

        employee.setId(1L);
        employee.setName("Gabriel");
        employee.setAge(26L);
        employee.setCep("93110270");
        employee.setSex("Masculino");

       // when(client.cep(employee.getCep())).then(cep);

    }

//    @Test
//    public void testCreateEmployee() throws Exception {
//        var employeer = service.create(employee);
//        verify(service).create(employeer);
//    }
}
