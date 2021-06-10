package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;

import java.util.ArrayList;
import java.util.Arrays;

public class PricesFixture {

    public static final int a_product_id = 35455;
    public static final int a_brand_id = 1;
    public static long a_datetime = 123456789;

    public static Price a_price = new Price(a_brand_id, a_product_id);
    public static Price another_price = new Price(a_brand_id, a_product_id);
    public static ArrayList<Price> some_prices = new ArrayList<>(Arrays.asList(a_price, another_price));

}
