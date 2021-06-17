package com.example.pricesexercise.core.domain;

import java.time.LocalDateTime;
import java.util.Date;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateToString;

public class Price {

    private final int brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final int priceList;
    private final int productId;
    private final int priority;
    private final float price;
    private final String currency;

    public Price(int brandId, LocalDateTime startDate, LocalDateTime endDate, int priceList,
                 int productId, int priority, float price, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public String toJsonObject() {
        return "{\n" +
            "brandId:" + brandId + ",\n" +
            "startDate:" + dateToString(startDate) + ",\n" +
            "endDate:" + dateToString(endDate) + ",\n" +
            "productId:" + productId + ",\n" +
            "price:" + price + ",\n" +
            "currency:" + currency + "\n" +
        "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (isAPrice(obj)) {
            final Price other = (Price) obj;
            return compareIds(other.brandId, other.productId)
                    && compareDates(other.startDate, other.endDate);
        }
        return false;
    }

    private boolean isAPrice(Object obj) {
        return (obj != null) && (obj.getClass() == this.getClass());
    }

    public boolean compareIds(int brandId, int productId) {
        return (this.brandId == brandId) && (this.productId == productId);
    }

    public boolean compareDates(LocalDateTime startDate, LocalDateTime endDate){
        return this.startDate.equals(startDate) && this.endDate.equals(endDate);
    }

    public int brandId() {
        return brandId;
    }

    public LocalDateTime startDate() {
        return this.startDate;
    }

    public LocalDateTime endDate() {
        return this.endDate;
    }

    public int priceList() {
        return this.priceList;
    }

    public int productId() {
        return this.productId;
    }

    public int priority() {
        return priority;
    }

    public float price() {
        return this.price;
    }

    public String currency() {
        return this.currency;
    }
}
