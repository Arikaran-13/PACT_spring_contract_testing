package com.example.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import com.example.consumer.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "demo",
        providerType = ProviderType.SYNCH,
        pactVersion = PactSpecVersion.V3
)
@PactDirectory("pacts")
class ConsumeTest {

    @Pact(consumer = "consumer", provider = "demo")
    public RequestResponsePact pactForGetBymployeeId(PactDslWithProvider provider) {
        PactDslJsonBody body = new PactDslJsonBody()
                .numberType("id", 1)
                .stringType("name", "Arikaran")
                .stringType("designation", "Software Engineer III");


        HashMap<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");

        return provider.given("Employee present with given id")
                .uponReceiving("Employee present with given id")
                .method("GET")
                .path("/employee/1")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pactForGetBymployeeId")
    public void test(MockServer server) {
        RestTemplate restTemplate = new RestTemplate();


        String uri = server.getUrl() + "/employee/1";


        EmployeeDto response = restTemplate.getForObject(uri, EmployeeDto.class);


        assertThat(response.getName()).isNotNull();

    }
}
