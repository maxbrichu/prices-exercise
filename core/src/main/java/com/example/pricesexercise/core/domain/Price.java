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
        if (is_a_price(obj)) {
            final Price other = (Price) obj;
            return has_ids(other.brandId, other.productId)
                    && has_dates(other.startDate, other.endDate);
        }
        return false;
    }

    private boolean is_a_price(Object obj) {
        return (obj != null) && (obj.getClass() == this.getClass());
    }

    public boolean has_ids(int brand_id, int product_id) {
        return (this.brandId == brand_id) && (this.productId == product_id);
    }

    public boolean has_dates(String startDate, String endDate){
        return this.startDate.equals(startDate) && (this.endDate.equals(endDate));
    }
}
