DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES (BRAND_ID INT(4) NOT NULL,
                    START_DATE TIMESTAMP NOT NULL,
                    END_DATE TIMESTAMP NOT NULL,
                    PRICE_LIST INT(4) NOT NULL,
                    PRODUCT_ID INT(10) NOT NULL,
                    PRIORITY INT(2) NOT NULL,
                    PRICE DECIMAL(10, 2) NOT NULL,
                    CURRENCY VARCHAR(3) NOT NULL);