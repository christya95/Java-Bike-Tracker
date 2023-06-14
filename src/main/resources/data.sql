INSERT INTO manufacturer(manufacturer) VALUES ('Honda');
INSERT INTO manufacturer(manufacturer) VALUES ('Tesla');
INSERT INTO manufacturer(manufacturer) VALUES ('BMW');
INSERT INTO manufacturer(manufacturer) VALUES ('Ford');

INSERT INTO bike(manufacturerID, model, year, colour, price) VALUES (1, 'RTX', 2001, 'Red', 50.34);
INSERT INTO bike(manufacturerID, model, year, colour, price) VALUES (3, 'CORSAIR', 2002, 'Green', 230.34);
INSERT INTO bike(manufacturerID, model, year, colour, price) VALUES (2, 'RTX', 2003, 'Pink', 2345.23);

insert into SEC_User (email, encryptedPassword, ENABLED)
values ('pooja@sheridancollege.ca', '$2a$04$Vr/mmR.7apfIM8QepzhcNeiSBuCm5z/fDZy05jMWdn7Kdlb4MFzHK', 1);

insert into SEC_User (email, encryptedPassword, ENABLED)
values ('josua@sheridancollege.ca', '$2a$04$fzkm59pTPt2tr7509Xs5WOWyU2H5wiNZf36TGPg8Pi8DIt6omEdoO', 1);

insert into sec_role (roleName)
values ('ROLE_ADMIN');
 
insert into sec_role (roleName)
values ('ROLE_USER');
 
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (1, 2);