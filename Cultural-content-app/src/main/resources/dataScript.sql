-- password is 'admin' (bcrypt encoded)
INSERT INTO user_table (email,password) VALUES ('admin@maildrop.cc', '$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK');
-- password is 'user' (bcrypt encoded)
INSERT INTO user_table (email,password) VALUES ('user@maildrop.cc', '$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq');

INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');

insert into user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into user_authority (user_id, authority_id) values (2, 2); -- user has ROLE_GUEST

INSERT INTO category (name) VALUES ('Institucija');
INSERT INTO category (name) VALUES ('Kulturno dobro');

INSERT INTO category_type (name,category_id) VALUES ('Muzej', 1);
INSERT INTO category_type (name,category_id) VALUES ('Galerija', 1);
INSERT INTO category_type (name,category_id) VALUES ('Spomenik', 2);
INSERT INTO category_type (name,category_id) VALUES ('Znamenitost', 2);
