DROP DATABASE IF EXISTS law_firm_client_base;

CREATE DATABASE law_firm_client_base WITH ENCODING 'UTF8';

SET client_encoding = 'UTF8';
SET client_min_messages = warning;

--SET LANGUAGE=ru;

\connect law_firm_client_base

SET client_encoding = 'UTF8';
SET client_min_messages = warning;
SET lc_messages TO 'en_US.UTF-8';


SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE services(
    service_id SERIAL PRIMARY KEY,
    service_type_name text,
    service_description text,
    service_approximate_cost integer
);

CREATE TABLE clients(
    client_id SERIAL PRIMARY KEY,
    client_fullname text NOT NULL,
    client_contact_phone decimal,
    client_email text,
    client_login text NOT NULL,
    client_password text NOT NULL
);

CREATE TABLE contracts(
    contract_id SERIAL PRIMARY KEY,
    client_id integer REFERENCES clients(client_id),
    service_type_id integer REFERENCES services(service_id),
    beginning_date date NOT NULL,
    date_of_completion date,
    additional_information text,
    real_cost integer
);

CREATE TABLE staff(
    employee_id SERIAL PRIMARY KEY,
    employee_fullname text NOT NULL,
    employee_address text,
    employee_phone_number decimal,
    employee_email text,
    employee_login text NOT NULL,
    employee_password text NOT NULL,
    employee_post text,
    employee_is_admin integer
);

CREATE TABLE employee_registered_service(
    employee_id integer REFERENCES staff(employee_id),
    registered_service_id integer REFERENCES contracts(contract_id)
);
