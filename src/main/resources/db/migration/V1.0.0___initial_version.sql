DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES (BrandId INT(4) NOT NULL,
                    startDate BIGINT NOT NULL,
                    endDate BIGINT NOT NULL,
                    priceList INT(4) NOT NULL,
                    productId INT(10) NOT NULL,
                    priority INT(2) NOT NULL,
                    price DECIMAL(10, 2) NOT NULL,
                    currency VARCHAR(3) NOT NULL);