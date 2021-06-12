package com.example.pricesexercise;

import com.example.pricesexercise.core.domain.Price;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PricesFixture {

    public static final int aBrandId = 1;
    public static String aStringDate = "2020-06-14-00.00.00";
    public static String anotherStringDate = "2020-12-31-23.59.59";
    public static int priceList = 1;
    public static final int aProductId = 35455;
    public static int aPriority = 0;
    public static float aPriceValue = 35.5f;
    public static String aCurrency = "EUR";

    public static String anInvalidValue = "INVALID VALUE";

    public static Price aPrice = new Price(aBrandId, aStringDate, anotherStringDate,
            priceList, aProductId, aPriority, aPriceValue, aCurrency);
    public static Price anotherPrice = new Price(aBrandId, aStringDate, anotherStringDate, priceList, aBrandId, aProductId, aPriceValue, aCurrency);
    public static ArrayList<Price> somePrices = new ArrayList<>(Arrays.asList(aPrice, anotherPrice));
    public static ArrayList<Price> emptyPrices = new ArrayList<>();

}
