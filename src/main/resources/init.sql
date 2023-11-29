CREATE TABLE insurance_companies (
                                     company_kpp INTEGER PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     address VARCHAR(255) NOT NULL
);

CREATE TABLE insurance_types (
                                 insurance_code SERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 description TEXT
);

CREATE TABLE clients (
                         client_number SERIAL PRIMARY KEY,
                         full_name VARCHAR(255) NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE policies (
                          policy_number SERIAL PRIMARY KEY,
                          client_number INTEGER REFERENCES clients (client_number) ON DELETE CASCADE,
                          insurance_code INTEGER REFERENCES insurance_types (insurance_code),
                          company_kpp INTEGER REFERENCES insurance_companies (company_kpp) ON DELETE CASCADE,
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL
);

CREATE TABLE payments (
                          payment_number SERIAL PRIMARY KEY,
                          policy_number INTEGER REFERENCES policies (policy_number) ON DELETE CASCADE,
                          payment_amount DECIMAL(10, 2) NOT NULL,
                          payment_date DATE NOT NULL
);
