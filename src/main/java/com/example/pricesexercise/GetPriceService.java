package com.example.pricesexercise;

import org.springframework.stereotype.Service;

@Service
public class GetPriceService {
    public Price execute(int brand_id, int product_id, long datetime ) {
        return new Price(brand_id, product_id);
    }
}
