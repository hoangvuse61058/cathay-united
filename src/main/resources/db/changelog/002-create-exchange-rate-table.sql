CREATE TABLE IF NOT EXISTS currency.exchange_rates (
    id BIGSERIAL PRIMARY KEY,
    base_currency VARCHAR(3) NOT NULL,
    quote_currency VARCHAR(3) NOT NULL,
    close_time VARCHAR(30) NOT NULL,
    average_bid VARCHAR(20),
    average_ask VARCHAR(20)
);