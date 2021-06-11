package com.example.pricesexercise;

import com.example.pricesexercise.core.domain.Price;

import java.util.ArrayList;
import java.util.Arrays;

public class PricesFixture {

    public static final int aBrandId = 1;
    public static String aDate = "2020-06-14-00.00.00";
    public static String another_date = "2020-12-31-23.59.59";
    public static int price_list = 1;
    public static final int aProductId = 35455;
    public static int a_priority = 0;
    public static float a_price_value = 35.5f;
    public static String a_currency = "EUR";

    public static String anInvalidValue = "INVALID VALUE";

    public static Price aPrice = new Price(aBrandId, aDate, another_date,
            price_list, aProductId, a_priority, a_price_value, a_currency);
    public static Price another_price = new Price(aBrandId, aDate, another_date, price_list, aBrandId, aProductId, a_price_value, a_currency);
    public static ArrayList<Price> some_prices = new ArrayList<>(Arrays.asList(aPrice, another_price));
    public static ArrayList<Price> empty_prices = new ArrayList<>();

}
