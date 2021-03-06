package br.jteodoro.wallet.api;

import br.jteodoro.wallet.WalletApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WalletApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles("test")
public class AccountEndpointTest {
    
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void whenCreatingAValidAccountShouldReturnCreated() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String body = "{ \"identifier\": \"" + uuid + "\" }";
        mvc.perform(post("/v1/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.identifier").value(uuid));
    }

    @Test
    public void whenGettingAValidAccountShouldReturnOkWithValues() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String body = "{ \"identifier\": \"" + uuid + "\", \"accountLimit\": \"100.00\" }";
        MvcResult response = mvc.perform(post("/v1/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.identifier").value(uuid))
        .andReturn();

        String id = ResponseHelper.getValueIdFromResponse(response);
        mvc.perform(get("/v1/accounts/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(id))
                .andExpect(jsonPath("$.identifier").value(uuid))
                .andExpect(jsonPath("$.accountLimit").value(100.0f));
    }

    @Test
    public void whenGettingAUnexistentccountShouldReturn404() throws Exception {
        mvc.perform(get("/v1/accounts/" + 404040404)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
