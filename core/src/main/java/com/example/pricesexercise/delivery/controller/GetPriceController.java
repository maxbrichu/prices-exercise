package com.example.pricesexercise.delivery.controller;

import com.example.pricesexercise.core.Provider;
import com.example.pricesexercise.core.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetPriceController {
    private final Provider provider;

    @Autowired
    public GetPriceController(Provider provider){
        this.provider = provider;
    }

    @GetMapping("/get_price/{brand_id}/{product_id}/{date}")
    public @ResponseBody
    String get_price(@PathVariable(value = "brand_id") int brand_id,
                     @PathVariable(value = "product_id") int product_id,
                     @PathVariable(value = "date") String date) {
        try {
            Price price = provider.get_price(brand_id, product_id, date);
            return price.toJsonObject();
        }catch (Exception e){
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}
