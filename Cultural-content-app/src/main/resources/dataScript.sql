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
--password: sifra6
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1005,'user6@mail.com', 'Stevan', 'Stevic', '
$2y$10$6TnlM2r/g0.Wgfko2w4Sa.DlapkUj9u0zBE7Cfxhh4o/nmzSf293O
', true);
--password: sifra7
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1006,'user7@mail.com', 'Zoran', 'Zoric', '
$2y$10$GdNs6ngrD2kB3fZbXw39LOZRq9gjooeVNm/sl2F6fPPJitygDBcF.
',true);
--password: sifra8
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1007,'user8@mail.com', 'Nikola', 'Markovic', '
$2y$10$D8atoj/MZY/MRmFmnwcI.etvkG6GuCnhVlFBnsyo42o13V.OFB4eS
', true);
--password: sifra9
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1008,'user9@mail.com', 'Stefan', 'Radojkovic', '
$2y$10$cKVJva/n6yuPSWNxx3aIm.YdBKBqwPtEWy9bJJT8h3W2Kk.5D4wj.
', true);
--password: sifra10
INSERT into registered_user (type, id, email, first_name, last_name, password, active) VALUES
('User',1009,'user10@mail.com', 'Dragan', 'Stokic', '
$2y$10$whsdGvABsbxDAbnBgou1..Jgwbnfa.XZzBCJ15pMxsFM5pVyQekj.
', true);


INSERT into authority(id, name) values (1, 'ROLE_USER');
INSERT into authority(id, name) values (2, 'ROLE_ADMIN');

insert into user_authority (user_id, authority_id) values (100, 2);
insert into user_authority (user_id, authority_id) values (1000, 1);
insert into user_authority (user_id, authority_id) values (1001, 1);
insert into user_authority (user_id, authority_id) values (1002, 1);
insert into user_authority (user_id, authority_id) values (1003, 1);
insert into user_authority (user_id, authority_id) values (1004, 1);
insert into user_authority (user_id, authority_id) values (1005, 1);
insert into user_authority (user_id, authority_id) values (1006, 1);
insert into user_authority (user_id, authority_id) values (1007, 1);
insert into user_authority (user_id, authority_id) values (1008, 1);
insert into user_authority (user_id, authority_id) values (1009, 1);

INSERT into cultural_offer_type (id,name) values (100,'Institucija');
INSERT into cultural_offer_type (id,name) values (101,'Manifestacija');
INSERT into cultural_offer_type (id,name) values (102,'Kulturno dobro');
INSERT into cultural_offer_type (id,name) values (103,'Soping');
INSERT into cultural_offer_type (id,name) values (104,'Sport');
INSERT into cultural_offer_type (id,name) values (105,'Gastronomija');
INSERT into cultural_offer_type (id,name) values (106,'Religija');


INSERT into cultural_offer_subtype(id, name, type_id) VALUES (101, 'Muzej', 100);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (102, 'Galerija', 100);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (103, 'Festival', 101);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (104, 'Sajam', 101);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (105, 'Spomenik', 102);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (106, 'Trzni centar', 103);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (107, 'Spa centar', 104);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (108, 'Teretana', 104);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (109, 'Sportski teren', 104);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (110, 'Stadion', 104);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (111, 'Crkva', 106);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (112, 'Manastir', 106);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (113, 'Arheoloski park', 102);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (114, 'Restorani', 105);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (115, 'Narodna kuhinja', 105);

INSERT into rating(id, comment, rating_value, user_id) VALUES (100, 'Dobar komentar', 4, 1000);
INSERT into rating(id, comment, rating_value, user_id) VALUES (101, 'Los komentar', 2, 1000);
INSERT into rating(id, comment, rating_value, user_id) VALUES (102, 'Dobar komentar', 3.5, 1000);
INSERT into rating(id, comment, rating_value, user_id) VALUES (103, 'Odlican komentar', 5, 1000);
INSERT into rating(id, comment, rating_value, user_id) VALUES (104, 'Los komentar', 2, 1000);
INSERT into rating(id, comment, rating_value, user_id) VALUES (105, 'Jako los komentar', 1, 1000);

INSERT into rating(id, comment, rating_value, user_id) VALUES (106, 'Dobar komentar', 4, 1001);
INSERT into rating(id, comment, rating_value, user_id) VALUES (107, 'Los komentar', 2, 1001);
INSERT into rating(id, comment, rating_value, user_id) VALUES (108, 'Dobar komentar', 3.5, 1001);

INSERT into rating(id, comment, rating_value, user_id) VALUES (109, 'Dobar komentar', 4, 1002);
INSERT into rating(id, comment, rating_value, user_id) VALUES (110, 'Los komentar', 2, 1002);
INSERT into rating(id, comment, rating_value, user_id) VALUES (111, 'Dobar komentar', 3.5, 1002);

INSERT into rating(id, comment, rating_value, user_id) VALUES (112, 'Dobar komentar', 4, 1003);
INSERT into rating(id, comment, rating_value, user_id) VALUES (113, 'Los komentar', 2, 1003);
INSERT into rating(id, comment, rating_value, user_id) VALUES (114, 'Dobar komentar', 3.5, 1003);

INSERT into rating(id, comment, rating_value, user_id) VALUES (115, 'Dobar komentar', 4, 1004);
INSERT into rating(id, comment, rating_value, user_id) VALUES (116, 'Los komentar', 2, 1004);
INSERT into rating(id, comment, rating_value, user_id) VALUES (117, 'Dobar komentar', 3.5, 1004);

INSERT into rating(id, comment, rating_value, user_id) VALUES (118, 'Dobar komentar', 4, 1005);
INSERT into rating(id, comment, rating_value, user_id) VALUES (119, 'Los komentar', 2, 1005);
INSERT into rating(id, comment, rating_value, user_id) VALUES (120, 'Dobar komentar', 3.5, 1005);

INSERT into rating(id, comment, rating_value, user_id) VALUES (121, 'Dobar komentar', 4, 1006);
INSERT into rating(id, comment, rating_value, user_id) VALUES (122, 'Los komentar', 2, 1006);
INSERT into rating(id, comment, rating_value, user_id) VALUES (123, 'Dobar komentar', 3.5, 1006);
INSERT into rating(id, comment, rating_value, user_id) VALUES (124, 'Dobar komentar', 4, 1006);
INSERT into rating(id, comment, rating_value, user_id) VALUES (125, 'Los komentar', 2, 1006);
INSERT into rating(id, comment, rating_value, user_id) VALUES (126, 'Dobar komentar', 3.5, 1006);


INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (100, 'Festival koji se odrzava u Novom Sadu', 'Festival čokolade', null, 103);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (101, 'Sajam u Beogradu', 'Sajam poljoprivrede', null, 104);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (102, 'Galerija u Pozarevcu', 'Galerija Milene Pavlovic Barili', null, 102);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (103, 'Trzni centar u Novom Sadu', 'Promenada', null, 106);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (104, 'Trzni centar u Beogradu', 'Delta siti', null, 106);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (105, 'Muzej u Beogradu', 'Muzej Nikole Tesle', null, 101);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (106, 'Opis', 'Manastir Rukumija', null, 112);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (107, 'Opis', 'Teretana Zmaj', null, 108);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (108, 'Opis', 'Teretana Core Fitness', null, 108);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (109, 'Stadion fudbalskog kluba Crvena Zvezda', 'Marakana', null, 110);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (110, 'Stadion fudbalskog kluba Partizan', 'JNA', null, 110);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (111, 'Arheolosko nalaziste kod Kostolca', 'Viminacijum', null, 113);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (112, 'Opis', 'Hram Svetog Save', null, 111);


INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (100, 100);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (101, 101);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (103, 102);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (106, 103);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (105, 104);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (110, 105);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (100, 106);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (103, 107);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (105, 108);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (101, 109);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (102, 110);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (103, 111);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (107, 112);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (108, 113);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (109, 114);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (101, 115);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (105, 116);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (107, 117);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (103, 118);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (111, 119);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (110, 120);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (104, 121);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (105, 122);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (107, 123);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (109, 124);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (111, 125);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (112, 126);


INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1000, 100);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1000, 101);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1001, 100);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1001, 104);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1002, 105);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1002, 106);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1003, 102);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1003, 104);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1004, 105);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1004, 106);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1005, 111);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1005, 112);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1006, 104);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1006, 107);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1007, 109);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1007, 102);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1008, 110);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1008, 111);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1009, 100);
INSERT into user_subscriptions(user_id, cultural_offer_id) VALUES (1009, 106);
