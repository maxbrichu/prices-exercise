package com.example.pricesexercise.delivery.controller;

import com.example.pricesexercise.core.action.GetPrice;
import com.example.pricesexercise.core.domain.Price;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static com.example.pricesexercise.delivery.controller.PricesFixture.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class GetPriceTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPrice getPrice;

    MvcResult mvcResult;

    @Test
    void get_price_for_datetime_2020_06_14_10_00_successfully() throws Exception {
        given_a_mocked_price_for_a_request(datetime_2020_06_14_10_00, a_price_2020_06_14_10_00);
        when_get_price(datetime_2020_06_14_10_00);
        then_get(a_price_2020_06_14_10_00);
    }

    private void given_a_mocked_price_for_a_request(long datetime, Price response) {
        when(getPrice.execute(a_brand_id, a_product_id, datetime)).thenReturn(response);
    }

    private void when_get_price(long datetime) throws Exception {
        String uri = String.format("/get_price/%s/%s/%s", a_brand_id, a_product_id, datetime);
        mvcResult = mockMvc.perform(get(uri))
                .andReturn();
    }

    private void then_get(Price expected_price) throws Exception {
        assertEquals(200, mvcResult.getResponse().getStatus());
        Price price = mapFromJson(mvcResult.getResponse().getContentAsString(), Price.class);
        assertEquals(price, expected_price);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}

