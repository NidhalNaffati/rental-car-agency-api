alter table car
    add constraint car_registration_number_unique unique (registration_number);

alter table customer
    add constraint customer_email_unique unique (email);

alter table dealer
    add constraint dealer_email_unique unique (email);

alter table car
    add constraint car_dealer_id_foreign_key
        foreign key (dealer_id)
            references dealer (id);

alter table transaction
    add constraint transaction_car_id_foreign_key
        foreign key (car_id)
            references car (id);

alter table transaction
    add constraint transaction_customer_id_foreign_key
        foreign key (customer_id)
            references customer (id);


alter table transaction
    add constraint transaction_dealer_id_foreign_key
        foreign key (dealer_id)
            references dealer (id);