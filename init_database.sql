SET client_encoding = 'UTF8';
SET client_min_messages = warning;

--SET LANGUAGE=ru;

\connect law_firm_client_base

SET client_encoding = 'UTF8';
SET client_min_messages = warning;
SET lc_messages TO 'en_US.UTF-8';


SET default_tablespace = '';

SET default_with_oids = false;

INSERT INTO services_description
(service_type_name, service_type_description, service_approximate_cost)
VALUES
('Защита потребителей', 'Любые услуги, консультации по защите потребителей', 100),
('Недвижимость', 'Всё, что связано с недвижимостью', 100);

INSERT INTO clients
(client_fullname, client_contact_phone, client_email, client_login, client_password)
VALUES
('Иванов Николай Петрович', 89156785678, 'ivnipe@gmail.com', 'ivnipe', '12345'),
('Жуков Иван Сергеевич', 89167390579, 'jukov@mail.ru', 'jukov', 'qwerty'),
('Масленников Пётр Александрович', 89267489380, 'erbb@yandex.ru', 'okcff', 'qwerty');

INSERT INTO registered_services
(client_id, service_type_id, beginning_date, date_of_completion, additional_information, real_cost)
VALUES
(1, 1, '2022-01-16', '2022-01-17', 'fkjn', 200),
(2, 1, '2022-02-23', '2022-02-25', 'jgl', 200),
(1, 2, '2022-01-16', '2022-01-17', 'jlghgj', 250);

INSERT INTO staff
(employee_fullname, employee_address, employee_phone_number, employee_email, employee_login, employee_password, employee_post, employee_is_admin)
VALUES
('Николаев Пётр Никифорович', 'df', 89150879678, 'niks@gmail.com', 'niks', 'dsfhgjhkjl', 'Администратор', 1),
('Юрьев Евгений Максимович', 'sd', 89163572938, 'uru@yandex.ru', 'uru', 'dfbhbdkfk', 'Юрист', 0),
('Алексеев Максим Петрович', 'sdds', 89268579709, 'aleks@mail.ru', 'aleks', 'lnddskbjn', 'Юрист', 0);

INSERT INTO employee_registered_service
(employee_id, registered_service_id)
VALUES
(2, 2),
(3, 1),
(3, 2);
