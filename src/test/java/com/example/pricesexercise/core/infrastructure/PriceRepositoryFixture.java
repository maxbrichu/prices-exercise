package com.example.pricesexercise.core.infrastructure;


import java.text.ParseException;

import static com.example.pricesexercise.PricesFixture.aStringStartDate;
import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToEpoch;

public class PriceRepositoryFixture {
    public static long aDateTime;

    static {
        try {
            aDateTime = dateStringToEpoch(aStringStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
