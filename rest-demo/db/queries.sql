

create table SOUVENIR (
    ID integer,
    NAME varchar(60),
    DESCRIPTION varchar(255),
    TOTAL_SOLD integer,
    NUM_IN_STOCK integer
);

insert into SOUVENIR (ID, NAME, DESCRIPTION, TOTAL_SOLD, NUM_IN_STOCK)
values (100, 'Arch Model', 'Plastic 24 inch model of the Gateway Arch', 7561, 12009);

insert into SOUVENIR (ID, NAME, DESCRIPTION, TOTAL_SOLD, NUM_IN_STOCK)
values (200, 'Ted Drewes T-Shirt', 'T-shirt with image of frozen custard mogul Ted Drewes', 1320, 3021);

insert into SOUVENIR (ID, NAME, DESCRIPTION, TOTAL_SOLD, NUM_IN_STOCK)
values (300, 'T-Ravs', '12 pound bag of frozen toasted ravioli', 29, 56);

ALTER USER sa SET PASSWORD 'LunchSA';
