package com.example.pricesexercise.delivery.controller;

import com.example.pricesexercise.core.Provider;
import com.example.pricesexercise.core.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class GetPriceController {
    private final Provider provider;
    public static final String GET_PRICE_URI = "/get_price/";
    public static final String GET_PRICE_ERROR_MESSAGE = "Bad request";

    @Lazy
    @Autowired
    public GetPriceController(Provider provider){
        this.provider = provider;
    }

    @GetMapping(GET_PRICE_URI + "{brand_id}/{product_id}/{date}")
    public @ResponseBody
    String get_price(@PathVariable(value = "brand_id") int brandId,
                     @PathVariable(value = "product_id") int productId,
                     @PathVariable(value = "date") String date) {
        try {
            Price price = provider.get_price(brandId, productId, date);
            return price.toJsonObject();
        }catch (Exception e){
            throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, GET_PRICE_ERROR_MESSAGE, e);
        }
    }
}
