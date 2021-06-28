package com.example.pricesexercise.integration;

import com.example.pricesexercise.core.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.UnsupportedEncodingException;

import static com.example.pricesexercise.PricesFixture.*;
import static com.example.pricesexercise.delivery.controller.GetPriceController.GET_PRICE_URI;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestApplicationConfig.class })
@WebAppConfiguration
public class IntegrationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    MvcResult mvcResult;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void components_validation() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("provider"));
        assertNotNull(webApplicationContext.getBean("getPriceController"));
    }

    @Test
    public void get_a_price_when_there_is_no_priority() throws Exception {
        when_get_price(aBrandId, aProductId, aStringStartDate);
        then_expected_result_is(aPrice);

    }

    @Test
    public void get_high_priority_price_successfully() throws Exception {
        when_get_price(aBrandId, aProductId, anotherStringStartDate);
        then_expected_result_is(aHighPriorityPrice);
    }

    private void when_get_price(int brandId, int productId, String date) throws Exception {
        String uri = String.format(GET_PRICE_URI + "%s/%s/%s", brandId, productId, date);
        mvcResult = this.mockMvc.perform(get(uri)).andReturn();
    }

    private void then_expected_result_is(Price price) throws UnsupportedEncodingException {
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(price.toJsonObject(), mvcResult.getResponse().getContentAsString());
    }
}
