package com.example.pricesexercise.core.infrastructure;

import static com.example.pricesexercise.PricesFixture.aStringStartDate;
import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToEpoch;

public class PriceRepositoryFixture {
    public static long aDateTime = dateStringToEpoch(aStringStartDate);
}
