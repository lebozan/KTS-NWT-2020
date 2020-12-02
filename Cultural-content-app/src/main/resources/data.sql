
INSERT INTO registered_user (type, id, email, first_name, last_name, password, role) VALUES ('Admin',1,'admin@mail.com','Bojan', 'Cakic','admin', 1);
INSERT into registered_user (type, id, email, first_name, last_name, password, role) VALUES ('User',2,'user@mail.com', 'Boban', 'Cakic', 'sifra', 0);


INSERT into cultural_offer_type (id,name) values (1,'Institucija');
INSERT into cultural_offer_type (id,name) values (2,'Manifestacija');
INSERT into cultural_offer_type (id,name) values (3,'Kulturno dobro');


INSERT into cultural_offer_subtype(id, name, type_id) VALUES (1, 'Muzej', 1);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (2, 'Galerija', 1);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (3, 'Festival', 2);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (4, 'Sajam', 2);
INSERT into cultural_offer_subtype(id, name, type_id) VALUES (5, 'Spomenik', 2);





