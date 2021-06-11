package com.example.pricesexercise.delivery.controller;

import com.example.pricesexercise.core.action.GetPrice;
import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.example.pricesexercise.delivery.controller.GetPriceController.getPriceErrorMessage;
import static com.example.pricesexercise.delivery.controller.GetPriceController.getPriceUri;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static com.example.pricesexercise.PricesFixture.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class GetPriceControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPrice getPrice;

    MvcResult mvcResult;

    @Test
    void get_price_successfully() throws Exception {
        given_a_mocked_price_for_a_request(aDate, aPrice);
        when_get_price(aDate);
        then_the_result_is(aPrice);
    }

    @Test
    void get_price_returns_an_error() throws Exception {
        when_get_price(anInvalidValue);
        then_expected_error_is(getPriceErrorMessage);
    }

    private void given_a_mocked_price_for_a_request(String date, Price response) throws PriceException {
        when(getPrice.execute(aBrandId, aProductId, date)).thenReturn(response);
    }

    private void when_get_price(String date) throws Exception {
        String uri = String.format(getPriceUri + "%s/%s/%s", aBrandId, aProductId, date);
        mvcResult = mockMvc.perform(get(uri))
                .andReturn();
    }

    private void then_the_result_is(Price expected_price) throws Exception {
        assertEquals(200, mvcResult.getResponse().getStatus());
        Price price = mapFromJson(mvcResult.getResponse().getContentAsString(), Price.class);
        assertEquals(price, expected_price);
    }

    private void then_expected_error_is(String expectedError) {
        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(expectedError, mvcResult.getResponse().getErrorMessage());
    }
    protected <T> T mapFromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}

