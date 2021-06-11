package com.example.pricesexercise.delivery.controller;
import com.example.pricesexercise.core.domain.Price;

public class PricesFixture {
    public static final int a_product_id = 35455;
    public static final int a_brand_id = 1;
    public static final String a_datetime = "2020-06-14-00.00.00";
    public static Price a_price = new Price(a_brand_id, a_product_id);

}
