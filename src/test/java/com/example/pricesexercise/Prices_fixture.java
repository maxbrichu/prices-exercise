package com.example.pricesexercise;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Prices_fixture {
    public static final int a_product_id = 35455;
    public static final int a_brand_id = 1;
    public static final long datetime_2020_06_14_10_00 =
            LocalDateTime.of(2020, 6, 14, 10, 0).toEpochSecond(ZoneOffset.UTC);

    public static Price a_price_2020_06_14_10_00 = new Price(a_brand_id, a_product_id);
}
