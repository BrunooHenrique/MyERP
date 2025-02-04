CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE supplier (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(14) NOT NULL
);