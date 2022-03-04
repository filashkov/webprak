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


CREATE TABLE services_description(
    service_type_id SERIAL PRIMARY KEY,
    service_type_name text,
    service_type_description text,
    service_approximate_cost integer
);

CREATE TABLE clients(
    client_id SERIAL PRIMARY KEY,
    client_fullname text NOT NULL,
    client_contact_phone integer,
    client_email text,
    client_login text NOT NULL,
    client_password text NOT NULL
);

CREATE TABLE registered_services(
    registered_service_id SERIAL PRIMARY KEY,
    client_id integer REFERENCES clients(client_id),
    service_type_id integer REFERENCES services_description(service_type_id),
    beginning_date date,
    date_of_completion date,
    additional_information text,
    real_cost integer
);

CREATE TABLE staff(
    employee_id SERIAL PRIMARY KEY,
    employee_fullname text,
    employee_address text,
    employee_phone_number integer,
    employee_email text,
    employee_login text,
    employee_password text,
    employee_post text,
    employee_is_admin integer
);

CREATE TABLE employee_registered_service(
    employee_id integer REFERENCES staff(employee_id),
    registered_service_id integer REFERENCES registered_services(registered_service_id)
);
