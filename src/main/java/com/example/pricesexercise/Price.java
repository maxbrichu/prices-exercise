package com.example.pricesexercise;

public class Price {

    private final int brand_id;
    private final int product_id;

    public Price(int brand_id, int product_id) {
        this.brand_id = brand_id;
        this.product_id = product_id;
    }

    public String toJsonObject() {
        return "{" +
            "brand_id:" + brand_id + "," +
            "product_id:" + product_id +
        "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Price other = (Price) obj;
        if (this.product_id != other.product_id) {
            return false;
        }

        if (this.brand_id != other.brand_id) {
            return false;
        }

        return true;
    }
}
