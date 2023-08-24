package com.example.demo;


import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringExtension;
import com.example.demo.service.DemoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@Provider("demo")
@WebMvcTest
@Consumer("consumer")
@PactBroker(host = "localhost", port = "9292")
public class ProviderTest {

    @BeforeAll
    static void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DemoService demoService;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
     void testVerify(PactVerificationContext context) {
        context.verifyInteraction();

    }

    @BeforeEach
    public void before(PactVerificationContext context) {

        context.setTarget(new MockMvcTestTarget(mockMvc));
    }

    @State("Employee present with given id")
    public void testEmployee() {

    }
}
