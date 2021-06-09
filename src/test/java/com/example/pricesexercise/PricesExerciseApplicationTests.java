package com.example.pricesexercise;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static com.example.pricesexercise.Prices_fixture.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PricesExerciseApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceService getPrice;

    MvcResult mvcResult;

    @Test
    void got_price_for_datetime_2020_06_14_10_00() throws Exception {
        given_a_price_for_a_request(a_brand_id, a_product_id, datetime_2020_06_14_10_00, a_price_2020_06_14_10_00);
        when_get_price(a_brand_id, a_product_id, datetime_2020_06_14_10_00);
        then_got(a_price_2020_06_14_10_00);
    }

    private void given_a_price_for_a_request(int brand_id, int product_id, long datetime, Price response) {
        when(getPrice.execute(brand_id, product_id, datetime)).thenReturn(response);
    }

    private void when_get_price(int brand_id, int product_id, long datetime) throws Exception {
        String uri = String.format("/get_price/%s/%s/%s", brand_id, product_id, datetime);
        mvcResult = mockMvc.perform(get(uri))
                .andReturn();
    }

    private void then_got(Price expected_price) throws Exception {
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Price price = mapFromJson(content, Price.class);
        assertEquals(price, expected_price);
    }

    protected String mapToJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}

