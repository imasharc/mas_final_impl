INSERT INTO COMPANY (ID, DTYPE, KRS, NAME, REGISTRATION_DATE, REGISTRATION_BUDGET, LICENSE_REGISTRATION)
VALUES
    (10001, 'Manufacturer', '000123456', 'Company One', '2021-01-01', 1000000.00, null),
    (20001, 'Carrier', '000654321', 'Company Two', '2021-02-01', null, '2021-02-02');

insert into
    address (id, appartment_number, city, postal_code, street, company_id)
values
    (10000, null, 'Warsaw', '03-734', 'ul. Targowa 74', 10001),
    (20000, null, 'Dębica', '39-200', 'ul. Sandomierska 39', 20001);