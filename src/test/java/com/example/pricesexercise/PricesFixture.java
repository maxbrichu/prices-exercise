package com.example.pricesexercise;

import com.example.pricesexercise.core.domain.Price;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToDate;

public class PricesFixture {

    public static final int aBrandId = 1;
    public static String aStringStartDate = "2020-06-14-00.00.00";
    public static String aStringEndDate = "2020-12-31-23.59.59";
    public static String anotherStringStartDate = "2020-06-14-15.00.00";
    public static String anotherStringEndDate = "2020-06-14-18.30.00";
    public static int priceList = 1;
    public static final int aProductId = 35455;
    public static int highPriority = 1;
    public static int lowPriority = 0;
    public static float aPriceValue = 35.5f;
    public static String aCurrency = "EUR";

    public static String anInvalidValue = "INVALID VALUE";

    public static Price aPrice;

    static {
        try {
            aPrice = new Price(aBrandId, dateStringToDate(aStringStartDate), dateStringToDate(aStringEndDate),
                    priceList, aProductId, lowPriority, aPriceValue, aCurrency);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Price aHighPriorityPrice;

    static {
        try {
            aHighPriorityPrice = new Price(aBrandId, dateStringToDate(anotherStringStartDate), dateStringToDate(anotherStringEndDate),
                    priceList, aProductId, highPriority, aPriceValue, aCurrency);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Price> somePrices = new ArrayList<>(Arrays.asList(aPrice, aHighPriorityPrice));
    public static ArrayList<Price> emptyPrices = new ArrayList<>();

}
