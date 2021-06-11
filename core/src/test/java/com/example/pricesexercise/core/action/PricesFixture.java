package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;

import java.util.ArrayList;
import java.util.Arrays;

public class PricesFixture {

    public static final int a_product_id = 35455;
    public static final int a_brand_id = 1;
    public static String a_datetime = "2020-06-14-00.00.00";

    // Devuelva como datos de salida: identificador de producto, identificador de cadena,
    // tarifa a aplicar, fechas de aplicación y precio final a aplicar.
    // BRAND_ID             = 1
    // START_DATE           = 2020-06-14-00.00.00
    // END_DATE             = 2020-12-31-23.59.59
    // PRICE_LIST           = 1
    // PRODUCT_ID           = 35455
    // PRIORITY             = 0
    // PRICE                = 35.50
    // CURR                 = EUR

    public static Price a_price = new Price(a_brand_id, a_product_id);
    public static Price another_price = new Price(a_brand_id, a_product_id);
    public static ArrayList<Price> some_prices = new ArrayList<>(Arrays.asList(a_price, another_price));

}
