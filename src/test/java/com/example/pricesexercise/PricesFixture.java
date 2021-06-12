package com.example.pricesexercise;

import com.example.pricesexercise.core.domain.Price;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToEpoch;

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

    public static Price aPrice;

    static {
        try {
            aPrice = new Price(aBrandId, dateStringToEpoch(aStringDate), dateStringToEpoch(anotherStringDate),
                    priceList, aProductId, aPriority, aPriceValue, aCurrency);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Price anotherPrice;

    static {
        try {
            anotherPrice = new Price(aBrandId, dateStringToEpoch(aStringDate), dateStringToEpoch(anotherStringDate),
                    priceList, aProductId, aPriority, aPriceValue, aCurrency);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Price> somePrices = new ArrayList<>(Arrays.asList(aPrice, anotherPrice));
    public static ArrayList<Price> emptyPrices = new ArrayList<>();

}
