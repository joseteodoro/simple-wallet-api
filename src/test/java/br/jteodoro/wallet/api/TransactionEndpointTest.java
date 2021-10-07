package br.jteodoro.wallet.api;

import br.jteodoro.wallet.WalletApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WalletApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class TransactionEndpointTest {
    
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    private String createRandomAccount() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String body = "{ \"identifier\": \"" + uuid + "\" }";
        MvcResult response = mvc.perform(post("/v1/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
        .andReturn();

        return ResponseHelper.getValueIdFromResponse(response);
    }

    @Test
    public void whenCreatingAValidTrxShouldReturnCreated() throws Exception {
        String accountId = createRandomAccount();
        String value = "23.41";
        String body = String.join("", 
          "{",
          "\"accountId\": ", accountId, ",",
          "\"operation\": ", "\"COMPRA_A_VISTA\"", ",",
          "\"value\": ", value,
          "}"
        );
        mvc.perform(post("/v1/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated());
    }

    @Test
    public void whenCreatingAValidDebitTrxShouldReturnPositiveValue() throws Exception {
        String accountId = createRandomAccount();
        String value = "23.41";
        String body = String.join("",
          "{",
          "\"accountId\": ", accountId, ",",
          "\"operation\": ", "\"COMPRA_A_VISTA\"", ",",
          "\"value\": ", value,
          "}"
        );
        mvc.perform(post("/v1/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.value").value(value));
    }

    @Test
    public void whenCreatingAValidCreditTrxShouldReturnPositiveValue() throws Exception {
        String accountId = createRandomAccount();
        String value = "23.41";
        String body = String.join("",
          "{",
          "\"accountId\": ", accountId, ",",
          "\"operation\": ", "\"PAGAMENTO\"", ",",
          "\"value\": ", value,
          "}"
        );
        mvc.perform(post("/v1/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.value").value(value));
    }

    private void createTrx(String accountId, String value, String op) throws Exception {
        String credit = String.join("",
          "{",
          "\"accountId\": ", accountId, ",",
          "\"operation\": ", "\"", op, "\"", ",",
          "\"value\": ", value,
          "}"
        );
        mvc.perform(post("/v1/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(credit)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated());
    }

    @Test
    public void whenCreatingAValidTrxsShouldListThemProperly() throws Exception {
        String accountId = createRandomAccount();
        String credit = "23.41";
        String debit = "11.17";
        createTrx(accountId, credit, "PAGAMENTO");
        createTrx(accountId, debit, "COMPRA_A_VISTA");

        mvc.perform(get("/v1/transactions/" + accountId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.[0].accountId").value(accountId))
            .andExpect(jsonPath("$.[0].value").value(credit))
            .andExpect(jsonPath("$.[0].operation").value("PAGAMENTO"))
            .andExpect(jsonPath("$.[1].accountId").value(accountId))
            .andExpect(jsonPath("$.[1].value").value(debit))
            .andExpect(jsonPath("$.[1].operation").value("COMPRA_A_VISTA"));
    }

    @Test
    public void whenCreatingTrxsForUnexistentAccountShouldReturn404() throws Exception {
        String credit = String.join("",
          "{",
          "\"accountId\": 400004000040004,",
          "\"operation\": ", "\"PAGAMENTO\"", ",",
          "\"value\": 23.15",
          "}"
        );
        mvc.perform(post("/v1/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(credit)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());
    }

    @Test
    public void whenGettingTrxsFromUnexistentAccountShouldReturn404() throws Exception {
        mvc.perform(get("/v1/transactions/40404040404")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
