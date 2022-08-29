package com.tracknme.desafio_tracknme.endpoint;

import com.tracknme.desafio_tracknme.domain.Employee;
import com.tracknme.desafio_tracknme.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeEndpointTest {

    @InjectMocks
    private EmployeeEndpoint endpoint;

    @Mock
    private EmployeeService service;

    private Employee employee = new Employee();

    @Before
    public void setUp() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Gabriel");
        employee.setAge(26L);
        employee.setCep("93110270");
        employee.setSex("Masculino");
        employee.setAddress("Rua Fernando Abott");
        employee.setDistrict("Rio dos Sinos");
        employee.setCity("São Leopoldo");
        employee.setState("RS");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setName("Adamy");
        employee2.setAge(10L);
        employee2.setCep("93110270");
        employee2.setSex("Masculino");
        employee2.setAddress("Rua Fernando Abott");
        employee2.setDistrict("Rio dos Sinos");
        employee2.setCity("São Leopoldo");
        employee2.setState("RS");

    }

    @Test
    public void testEndPoint() throws Exception {
        endpoint.create(employee);
        verify(service, times(1)).create(employee);
    }

    @Test
    public void testEndPointDelete() {
        endpoint.delete(anyLong());
        verify(service, times(1)).delete(anyLong());
    }

    @Test
    public void testEndPointUpdate() {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Gabriel");
        e.setAge(26L);
        e.setCep("93110270");
        e.setSex("Masculino");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", e.getName());
        parameters.put("age", e.getAge().toString());
        parameters.put("cep", e.getCep());
        endpoint.update(1L, parameters);
        verify(service, times(1)).updatePatch(1L, parameters);
    }
}
