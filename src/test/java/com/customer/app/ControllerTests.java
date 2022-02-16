package com.customer.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
public class ControllerTests {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper om;

    @Autowired
    private void setupOM() {
        om = new ObjectMapper();
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private void testSave(String url, Customer c) throws Exception {
        String json = om.writeValueAsString(c);
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(json)
                .contentType(APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    public void testSaveCorrectData()throws Exception{
        Customer c = new Customer(
                "Ojciec Pio",
                "ale juz nie Pijo",
                "56031512345"
        );
        String url = "http://localhost:8081/custonmer/add";
        testSave(url, c);
    }

    @Test
    public void testSaveIncorrectData()throws Exception{
        Customer c = new Customer(
                "Ojciec Pio",
                "ale juz nie Pijo",
                "56031512"
        );
        String url = "http://localhost:8081/customer/add";
        testSave(url, c);
    }

}



