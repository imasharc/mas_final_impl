INSERT INTO COMPANY (ID, DTYPE, KRS, NAME, REGISTRATION_DATE, REGISTRATION_BUDGET, LICENSE_REGISTRATION)
VALUES
    (10001, 'Manufacturer', '000123456', 'Company One', '2021-01-01', 1000000.00, null),
    (20001, 'Carrier', '000654321', 'Company Two', '2021-02-01', null, '2021-02-02');

insert into
    account (id, login, password, company_id)
values
    (10002, '000123456', 'pancakes', 10001),
    (20002, '000654321', 'lollipop123', 20001);

insert into
    address (id, appartment_number, city, postal_code, street, company_id)
values
    (10000, null, 'Warsaw', '03-734', 'ul. Targowa 74', 10001),
    (20000, null, 'Dębica', '39-200', 'ul. Sandomierska 39', 20001);

insert into
    train (id, dtype, num_of_wagons, registration_date, train_lead_price, manufacturer_id, license, model, train_code)
values
    (10003, 'SteamTrain', 12, '2021-01-01', 13233.0, 10001, '3534', 'RAWAG', 'B313J');