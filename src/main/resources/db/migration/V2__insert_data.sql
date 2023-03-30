insert into car_seq
values (18);

insert into customer_seq
values (4);

insert into dealer_seq
values (21);

insert into transaction_seq
values (4);


INSERT INTO dealer (id, email, first_name, last_name)
VALUES (1, 'DavidSmith@example.com', 'David', 'Smith'),
       (2, 'TomJohnson@example.com', 'Tom', 'Johnson'),
       (3, 'LucyChen@example.com', 'Lucy', 'Chen'),
       (4, 'JohnDoe@example.com', 'John', 'Doe'),
       (5, 'JaneDoe@example.com', 'Jane', 'Doe'),
       (6, 'RobertSmith@example.com', 'Robert', 'Smith'),
       (7, 'EmilyJones@example.com', 'Emily', 'Jones'),
       (8, 'MichaelJohnson@example.com', 'Michael', 'Johnson'),
       (9, 'SaraLee@example.com', 'Sara', 'Lee'),
       (10, 'KevinNguyen@example.com', 'Kevin', 'Nguyen'),
       (11, 'LindaTran@example.com', 'Linda', 'Tran'),
       (12, 'ChrisWilson@example.com', 'Chris', 'Wilson'),
       (13, 'EricaDavis@example.com', 'Erica', 'Davis'),
       (14, 'StevenLee@example.com', 'Steven', 'Lee'),
       (15, 'AmandaJohnson@example.com', 'Amanda', 'Johnson'),
       (16, 'PatrickSmith@example.com', 'Patrick', 'Smith'),
       (17, 'AshleyBrown@example.com', 'Ashley', 'Brown'),
       (18, 'JacobNguyen@example.com', 'Jacob', 'Nguyen'),
       (19, 'KarenWang@example.com', 'Karen', 'Wang'),
       (20, 'MattKim@example.com', 'Matt', 'Kim');

INSERT INTO car (id, name, registration_number, seats, kilometres, doors, model, gear, fuel, dealer_id)
VALUES (1, 'Toyota Corolla', 12345, 5, 10000, 4, 'SEDAN', 'Automatic', 'Petrol', 1),
       (2, 'Ford Mustang', 67890, 4, 5000, 2, 'SPORTS_CAR', 'Manual', 'Petrol', 1),
       (3, 'Toyota Prius', 13579, 5, 20000, 4, 'SEDAN', 'Automatic', 'Diesel', 1),
       (4, 'Honda Civic', 24681, 5, 8000, 4, 'SEDAN', 'Automatic', 'Petrol', 2),
       (5, 'Honda Civic', 24680, 5, 8000, 4, 'SEDAN', 'Automatic', 'Petrol', 2),
       (6, 'BMW X5', 24601, 5, 15000, 4, 'SUV', 'Automatic', 'Diesel', 2),
       (7, 'Audi A4', 98765, 5, 12000, 4, 'SEDAN', 'Automatic', 'Petrol', 3),
       (8, 'Chevrolet Camaro', 54321, 4, 7000, 2, 'SPORTS_CAR', 'Manual', 'Petrol', 3),
       (9, 'Dodge Challenger', 56789, 4, 10000, 2, 'SPORTS_CAR', 'Manual', 'Petrol', 3),
       (10, 'Chevrolet Impala', 55555, 5, 9000, 4, 'SEDAN', 'Automatic', 'Petrol', 4),
       (11, 'Jeep Grand Cherokee', 12121, 5, 18000, 4, 'SUV', 'Automatic', 'Diesel', 4),
       (12, 'Ford Escape', 10101, 5, 12000, 4, 'SUV', 'Automatic', 'Petrol', 4),
       (13, 'Toyota RAV4', 20202, 5, 10000, 4, 'SUV', 'Automatic', 'Petrol', 5),
       (14, 'Honda CR-V', 30303, 5, 15000, 4, 'SUV', 'Automatic', 'Petrol', 5),
       (15, 'Nissan Rogue', 40404, 5, 12000, 4, 'SUV', 'Automatic', 'Petrol', 5),
       (16, 'Kia Sorento', 50505, 7, 16000, 4, 'SUV', 'Automatic', 'Diesel', 6),
       (17, 'Hyundai Tucson', 60606, 5, 11000, 4, 'SUV', 'Automatic', 'Petrol', 6);


INSERT INTO customer (id, email, first_name, last_name)
VALUES (1, 'john.doe@example.com', 'John', 'Doe'),
       (2, 'jane.smith@example.com', 'Jane', 'Smith'),
       (3, 'jimmy.nguyen@example.com', 'Jimmy', 'Nguyen');


INSERT INTO transaction (id, date, customer_id, dealer_id, car_id)
VALUES (1, '2022-01-01 10:00:00', 1, 1, 1),
       (2, '2022-02-15 14:30:00', 2, 1, 2),
       (3, '2022-03-23 09:45:00', 3, 2, 3);
