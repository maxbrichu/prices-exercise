package com.example.pricesexercise;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.domain.PriceException;

import java.util.ArrayList;
import java.util.Arrays;

public class PricesFixture {

    public static final int a_brand_id = 1;
    public static String a_date = "2020-06-14-00.00.00";
    public static String another_date = "2020-12-31-23.59.59";
    public static int price_list = 1;
    public static final int a_product_id = 35455;
    public static int a_priority = 0;
    public static float a_price_value = 35.5f;
    public static String a_currency = "EUR";

    public static String an_invalid_value = "INVALID VALUE";
    public static PriceException invalid_date_exception = new PriceException("Invalid Date");

    public static Price a_price = new Price(a_brand_id, a_date, another_date,
            price_list, a_product_id, a_priority, a_price_value, a_currency);
    public static Price another_price = new Price(a_brand_id, a_date, another_date, price_list, a_brand_id, a_product_id, a_price_value, a_currency);
    public static ArrayList<Price> some_prices = new ArrayList<>(Arrays.asList(a_price, another_price));

}
