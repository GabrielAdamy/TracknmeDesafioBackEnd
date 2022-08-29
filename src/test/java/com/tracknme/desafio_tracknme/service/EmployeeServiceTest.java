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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
    public void setUp() throws Exception {
        employee = new Employee();

        employee.setId(1L);
        employee.setName("Gabriel");
        employee.setAge(26L);
        employee.setCep("93110270");
        employee.setSex("Masculino");
        employee.setAddress("Rua Fernando Abott");
        employee.setCity("São Leopoldo");
        employee.setState("RS");
        employee.setDistrict("Rio dos Sinos");

        cep.setLogradouro("Rua Fernando Abott");
        cep.setLocalidade("São Leopoldo");
        cep.setUf("RS");
        cep.setBairro("Rio dos Sinos");
        cep.setCep("93110270");

        when(client.cep(employee.getCep())).thenReturn(cep);
        when(service.create(employee)).thenReturn(employee);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(employee));

    }


    @Test
    public void testCreateEmployee() throws Exception {
        var employeer = service.create(employee);
        assertEquals("Gabriel", employeer.getName());
        assertEquals("Rua Fernando Abott", employeer.getAddress());
    }

    @Test
    public void testDeleteEmployee(){
        service.delete(1L);
    }

    @Test
    public void testFindByIdEmployee(){
        var result = service.findById(1L);
        assertEquals(employee, result);
    }

    @Test
    public void testUpdate() {
        var result = service.findById(1L);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "Adamy");
        parameters.put("cep", result.getCep());
        parameters.put("sex", "Masculino");

        service.updatePatch(1L, parameters);
        assertEquals(employee.getName(), "Adamy");
    }
}
