package com.example.pricesexercise.core.domain;

public class Price {

    private final int brandId;
    private final String startDate;
    private final String endDate;
    private final int priceList;
    private final int productId;
    private final int priority;
    private final float priceValue;
    private final String currency;

    public Price(int brandId, String startDate, String endDate, int priceList,
                 int productId, int priority, float priceValue, String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.priceValue = priceValue;
        this.currency = currency;
    }

    public String toJsonObject() {
        return "{" +
            "brandId:" + brandId + "," +
            "startDate:" + startDate + "," +
            "endDate:" + endDate + "," +
            "productId:" + productId + "," +
            "priceValue:" + priceValue + "," +
            "currency:" + currency +
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

    public boolean compareDates(String startDate, String endDate){
        return this.startDate.equals(startDate) && (this.endDate.equals(endDate));
    }
}
