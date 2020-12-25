
--password: admin
INSERT INTO registered_user (type, id, email, first_name, last_name, password, active) VALUES
('Admin',100,'admin@mail.com','Bojan', 'Cakic','$2y$12$YEZzLa8qmv69iere8iQzkO7AkJtcigLOrFI7jUSNRgYvTd2IZJZXq', true);
--password: sifra1
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1000,'user1@mail.com', 'Boban', 'Cakici', '$2y$12$CIGXApWSB7kY.vM96p8G8emv2CuMhaF44XFBIjll7Zw0OjRP86Pn6', true);
--password: sifra2
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1001,'user2@mail.com', 'Petar', 'Petrovic', '$2y$12$ojCqzJu6Q6xLrL6aiVcR0u96kX8Ib13VyY/iv9qurzrFjB72.JeHi',true);
--password: sifra3
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1002,'user3@mail.com', 'Nikola', 'Nikolic', '$2y$12$My.CsANpGVQQ4ciMo9A9Q.hDe7PNJ5RvByN20GrUiDc3Kj9M3hApG', true);
--password: sifra4
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1003,'user4@mail.com', 'Stefan', 'Stefanovic', '$2y$12$EYBdLPMKmbiqqifuY6B92.3k9M5TrqeFsUxWoo6Q7etsD6UPSzx/m', true);
--password: sifra5
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1004,'user5@mail.com', 'Milan', 'Milanovic', '$2y$12$7CFtOhdi9R.EkYX9CXVptOfkI85irVJnDI2flxIFK8s2wPj3ro7qq', true);

INSERT into authority(id, name) values (1, 'ROLE_USER');
INSERT into authority(id, name) values (2, 'ROLE_ADMIN');

insert into user_authority (user_id, authority_id) values (100, 2);
insert into user_authority (user_id, authority_id) values (1000, 1);
insert into user_authority (user_id, authority_id) values (1001, 1);
insert into user_authority (user_id, authority_id) values (1002, 1);
insert into user_authority (user_id, authority_id) values (1003, 1);
insert into user_authority (user_id, authority_id) values (1004, 1);


INSERT into cultural_offer_type (id,name) values (100,'Institucija');
INSERT into cultural_offer_type (id,name) values (101,'Manifestacija');
INSERT into cultural_offer_type (id,name) values (102,'Kulturno dobro');


INSERT into cultural_offer_subtype(id, name, type_id) VALUES (101, 'Muzej', 100);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (102, 'Galerija', 100);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (103, 'Festival', 101);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (104, 'Sajam', 101);
-- INSERT into cultural_offer_subtype(id, name, type_id) VALUES (105, 'Spomenik', 103);


INSERT into rating(id, comment, rating_value, user_id) VALUES (100, 'Dobar komentar', 4, 1000);
INSERT into rating(id, comment, rating_value, user_id) VALUES (101, 'Los komentar', 2, 1000);
INSERT into rating(id, comment, rating_value, user_id   ) VALUES (102, 'Dobar komentar', 3.5, 1001);

INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (100, 'Opis', 'Festival čokolade', null, 103);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (101, 'Opis', 'Sajam poljoprivrede', null, 104);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (102, 'Opis', 'Galerija Milene Pavlovic Barili', null, 102);

INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (100, 100);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (102, 101);

INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1000, 100);

INSERT into image(id, addressurl) values ( 100, 'https://asdqwepoldsgoej' );
INSERT into image(id, addressurl) values ( 101, 'https://asdqweasdzxc' );