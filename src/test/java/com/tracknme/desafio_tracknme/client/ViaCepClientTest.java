package com.tracknme.desafio_tracknme.client;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(MockitoJUnitRunner.class)
public class ViaCepClientTest {

    @InjectMocks
    private ViaCepClient client;

    @Before
    public void setUp() {}

    @Test
    public void cepValid() throws Exception {
        ViaCepClient client = new ViaCepClient();
        var cep = client.cep("93110270");
        Assert.assertTrue(cep.getBody().getLogradouro().equalsIgnoreCase("Rua Fernando Abott"));
        Assert.assertTrue(cep.getBody().getBairro().equalsIgnoreCase("Rio dos Sinos"));

    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void cepInvalid() throws Exception {
        ViaCepClient client = new ViaCepClient();
        client.cep("2093004");
    }
}
