
INSERT INTO registered_user (type, id, email, first_name, last_name, password, role, active) VALUES ('Admin',100,'admin@mail.com','Bojan', 'Cakic','admin', 0, true);

INSERT into registered_user (type, id, email, first_name, last_name, password, role, active) VALUES ('User',1000,'user1@mail.com', 'Boban', 'Cakic', 'sifra', 1, true);
INSERT into registered_user (type, id, email, first_name, last_name, password, role, active) VALUES ('User',1001,'user2@mail.com', 'Petar', 'Petrovic', 'sifra', 1, true);
INSERT into registered_user (type, id, email, first_name, last_name, password, role, active) VALUES ('User',1002,'user3@mail.com', 'Nikola', 'Nikolic', 'sifra', 1, true);
INSERT into registered_user (type, id, email, first_name, last_name, password, role, active) VALUES ('User',1003,'user4@mail.com', 'Stefan', 'Stefanovic', 'sifra', 1, true);
INSERT into registered_user (type, id, email, first_name, last_name, password, role, active) VALUES ('User',1004,'user5@mail.com', 'Milan', 'Milanovic', 'sifra', 1, true);


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
INSERT into rating(id, comment, rating_value, user_id) VALUES (102, 'Dobar komentar', 3.5, 1001);

INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (100, 'Opis', 'Festival čokolade', null, 103);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (101, 'Opis', 'Sajam poljoprivrede', null, 104);
INSERT into cultural_offer(id, description, name, location_id, subtype_id) VALUES (102, 'Opis', 'Galerija Milene Pavlovic Barili', null, 102);

INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (100, 100);
INSERT into cultural_offer_ratings(cultural_offer_id, ratings_id) VALUES (102, 101);
