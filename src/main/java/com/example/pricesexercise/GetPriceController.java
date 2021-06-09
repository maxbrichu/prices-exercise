package com.example.pricesexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetPriceController {
    @Autowired
    private GetPriceService getPrice;

    @GetMapping("/get_price/{brand_id}/{product_id}/{datetime}")
    public @ResponseBody
    String get_price(@PathVariable(value = "brand_id") int brand_id,
                     @PathVariable(value = "product_id") int product_id,
                     @PathVariable(value = "datetime") long datetime) {
        Price price = getPrice.execute(brand_id, product_id, datetime);
        return price.toJsonObject();
    }
}
