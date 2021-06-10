package com.example.pricesexercise.core.domain;

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
        if (is_a_price(obj)) {
            final Price other = (Price) obj;
            return has_ids(other.brand_id, other.product_id);
        }
        return false;
    }

    private boolean is_a_price(Object obj) {
        return (obj != null) && (obj.getClass() == this.getClass());
    }

    public boolean has_ids(int brand_id, int product_id) {
        return (this.brand_id == brand_id) && (this.product_id == product_id);
    }
}
