package com.example.pricesexercise.core.domain;

import java.time.LocalDateTime;

import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateStringToDate;
import static com.example.pricesexercise.core.infrastructure.utils.DateUtils.dateToString;

public class Price {

    private final int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private final int productId;
    private int priority;
    private float price;
    private String currency;

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

    public Price(String[] attributes) {
        this.brandId = Integer.parseInt(attributes[0]);
        try {
            this.startDate = dateStringToDate(attributes[1]);
            this.endDate = dateStringToDate(attributes[2]);
        } catch (Exception ignored){
            this.startDate = null;
            this.endDate = null;
        }

        this.priceList = Integer.parseInt(attributes[3]);
        this.productId = Integer.parseInt(attributes[4]);
        this.priority  = Integer.parseInt(attributes[5]);
        this.price  = Float.parseFloat(attributes[6]);
        this.currency= attributes[7];
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

    public void update(Price price) {
        this.priceList = price.priceList;
        this.priority = price.priority;
        this.price = price.price;
        this.currency = price.currency;
    }
}
