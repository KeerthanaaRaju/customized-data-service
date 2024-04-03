CREATE TABLE products (
    productId VARCHAR(255) PRIMARY KEY,
    category VARCHAR(255),
    brand VARCHAR(255)
);

CREATE TABLE shoppers (
    shopperId VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE shopper_product_relevance (
    shopperId VARCHAR(255) REFERENCES shoppers(shopperId),
    productId VARCHAR(255) REFERENCES products(productId),
    relevancyScore double precision,
    PRIMARY KEY (shopperId, productId)
);