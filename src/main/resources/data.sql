INSERT INTO users ( username, password, enabled, apikey, email)
VALUES ( 'user@nl.nl', '$2a$12$8l8G9.kf.foUupHgCHYM3.LHw8ANhNp250T7wkpB9xL390I0OKm9e', true, 111, 'user@nl.nl');
INSERT INTO authorities (username, authority)
VALUES ('user@nl.nl', 'ROLE_USER');

INSERT INTO users ( username, password, enabled, apikey,email)
VALUES ( 'admin@nl.nl', '$2a$12$Xpni1jOxCYAEJyJgf9yqo.44o8EEkcWVsfEKnjwjd.qfov0WB7XM6', true, 222, 'admin@nl.nl');
INSERT INTO authorities (username, authority)
VALUES ('admin@nl.nl', 'ROLE_ADMIN');

INSERT INTO users ( username, password, enabled, apikey, email)
VALUES ( 'jftalavera@hotmail.com', '$2a$12$vzybNIIL34IK5CrtOpLBveE/219wFcRN5ghFWJ0WKUywJfX4e9Lfm', true, 333, 'jftalavera@hotmail.com'); --talavera
INSERT INTO authorities (username, authority)
VALUES ('jftalavera@hotmail.com', 'ROLE_ADMIN');

INSERT INTO users ( username, password, enabled, apikey, email) VALUES('User1', '$2a$12$7kOvnzB.nI7AWFFpSAZ1Q.xH3f90TUK2vOwQ1Eq.QMZhn.ul0nD4a', true, '', 'user1@test.nl');
INSERT INTO users ( username, password, enabled, apikey, email) VALUES('User2', '$2a$12$HO8Xax.52fuTSzmM0zpW7OjWKHJYn32bslwgRalYvch7ZeWeiDJWS', true, '', 'user2@test.nl');
INSERT INTO users ( username, password, enabled, apikey, email) VALUES('User3', '$2a$12$ZXhEbUVs71kvT/T6GDOwk.i.IbyU0vuiKj9GEt9GjY.D0kZkr/XqK', true, '', 'user3@test.nl');
INSERT INTO users ( username, password, enabled, apikey, email) VALUES('User4', '$2a$12$U0Zwrz40h2JNOKow5Y3RGem/Zv2d8zaiHdF3n5SK4kpQ4Ew8aX0ke', true, '', 'user4@test.nl');
INSERT INTO users ( username, password, enabled, apikey, email) VALUES('User5', '$2a$12$TtmhuzuISG7CpCO3kQBow.QYudV7wRh0GDlXwHh0LYQnx.UHHUzLe', true, '', 'user5@test.nl');
INSERT INTO users ( username, password, enabled, apikey, email) VALUES('User6', '$2a$12$vk5Y/w4oAlhXf.hTWSi8wO0luz/UbquMmTysZ81BhsWa66oikjoRK', true, '', 'user6@test.nl');

INSERT INTO authorities (username, authority) VALUES ('User1', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('User2', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('User3', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('User4', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('User5', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('User6', 'ROLE_USER');


INSERT INTO items ( name_Info,  user_username) VALUES ( 'bag of leather', (select username from users where username = 'User1'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'bag2 of leather', (select username from users where username = 'User2'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'blouse of silk', (select username from users where username = 'User3'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'coat of linen', (select username from users where username = 'User4'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'coat of silk', (select username from users where username = 'User5'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'Gloss of alpaca wool', (select username from users where username = 'User6'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'coat of wool', (select username from users where username = 'User7'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'coat of cotton', (select username from users where username = 'User8'));
INSERT INTO items ( name_Info,  user_username) VALUES ( 'hat of cotton', (select username from users where username = 'User9'));



--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'dress of silk', '', (select username from users where username = 'User1'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'hat of cotton', '', (select username from users where username = 'User2'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'blouse of silk', '', (select username from users where username = 'User3'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'skirt of silk', '', (select username from users where username = 'User4'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'bag of leather', '', (select username from users where username = 'User5'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ('coat of silk', '', (select username from users where username = 'User6'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'coat of wool', '', (select username from users where username = 'User7'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ( 'coat of cotton', '', (select username from users where username = 'User8'));
--INSERT INTO items ( name_Info, upload_file_name, user_username) VALUES ('coat of linen', '', (select username from users where username = 'User9'));





INSERT INTO item_tags(item_id, tags) VALUES (1, 'SILK');
INSERT INTO item_tags(item_id, tags) VALUES (1, 'ORGANIC');

INSERT INTO item_tags(item_id, tags) VALUES (2, 'COTTON');
INSERT INTO item_tags(item_id, tags) VALUES (2, 'MINIMALISTIC');

INSERT INTO item_tags(item_id, tags) VALUES (3, 'SILK');
INSERT INTO item_tags(item_id, tags) VALUES (3, 'SUSTAINABLE');

INSERT INTO item_tags(item_id, tags) VALUES (4, 'WOOL');
INSERT INTO item_tags(item_id, tags) VALUES (4, 'ADDITIVE_FREE');

INSERT INTO item_tags(item_id, tags) VALUES (5, 'LEATHER');
INSERT INTO item_tags(item_id, tags) VALUES (5, 'ADDITIVE-FREE');

INSERT INTO item_tags(item_id, tags) VALUES (6, 'LINEN');
INSERT INTO item_tags(item_id, tags) VALUES (6, 'NON_CHEMICAL');

INSERT INTO item_tags(item_id, tags) VALUES (7, 'COTTON');
INSERT INTO item_tags(item_id, tags) VALUES (7, 'PESTICIDE_FREE');

INSERT INTO item_tags(item_id, tags) VALUES (8, 'COTTON');
INSERT INTO item_tags(item_id, tags) VALUES (8, 'ADDITIVE-FREE');

INSERT INTO item_tags(item_id, tags) VALUES (9, 'SILK');
INSERT INTO item_tags(item_id, tags) VALUES (9, 'ORGANIC');



INSERT INTO uploads (file_name, text_Type, url) VALUES ('bag of leather.jpg', 'image/jpeg', 'http://localhost:8083/download/bagofleather.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('bag2 of leather.jpg', 'image/jpeg', 'http://localhost:8083/download/bag2ofleather.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('blouse of silk.jpg','image/jpeg', 'http://localhost:8083/download/blouseofsilk.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('coat of linen.jpg','image/jpeg', 'http://localhost:8083/download/coatoflinen.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('coat of silk.jpg','image/jpeg', 'http://localhost:8083/download/coatofsilk.jpg');
--INSERT INTO uploads (file_name, text_Type, url) VALUES ('Gloss of alpaca wool.jpg','image/jpeg', 'http://localhost:8083/download/Glossofalpacawool.jpg');


--INSERT INTO uploads (file_name, text_Type, url) VALUES ('dress of silk.jpg','image/jpg', 'http://localhost:8083/download/dress.jpg');
--INSERT INTO uploads (file_name, text_Type, url) VALUES ('hat of Linen.jpg', 'image/jpg', 'http://localhost:8083/download/hat1.jpg');

--INSERT INTO uploads (file_name, text_Type, url) VALUES ('skirt.jpg', 'image/jpg', 'http://localhost:8083/download/skirt.jpg');
--INSERT INTO uploads (file_name, text_Type, url) VALUES ('bag.jpg', 'image/jpg', 'http://localhost:8083/download/bag.jpg');
--INSERT INTO uploads (file_name, text_Type, url) VALUES ('hat.jpg', 'image/jpg', 'http://localhost:8083/download/hat.jpg');






INSERT INTO accounts ( user_Info, subscription_Info, user_username , subscription_id) VALUES ( 'User1', 'annual',(select username from users where username = 'User1'),
                      (select subscription_id from subscription_status where subscription_id = 1) );

INSERT INTO accounts ( user_Info, subscription_Info, user_username , subscription_id) VALUES ( 'User2', 'annual', (select username from users where username = 'User2'),
                       (select subscription_id from subscription_status where subscription_id = 2));

INSERT INTO accounts ( user_Info, subscription_Info, user_username , subscription_id) VALUES ( 'User3', 'annual',(select username from users where username = 'User3'),
                      (select subscription_id from subscription_status where subscription_id = 3) );

INSERT INTO accounts ( user_Info, subscription_Info, user_username , subscription_id) VALUES ( 'User4', 'annual',(select username from users where username = 'User4'),
                     (select subscription_id from subscription_status where subscription_id = 4) );

INSERT INTO accounts ( user_Info, subscription_Info, user_username , subscription_id) VALUES ( 'User5', 'annual',(select username from users where username = 'User5'),
                      (select subscription_id from subscription_status where subscription_id = 5) );

INSERT INTO accounts ( user_Info, subscription_Info, user_username , subscription_id) VALUES ( 'User6', 'annual',(select username from users where username = 'User6'),
                      (select subscription_id from subscription_status where subscription_id = 6) );




INSERT INTO subscriptions (id, type, expiration_Date) VALUES (1, 'annual', '2023-12-01');
INSERT INTO subscriptions (id, type, expiration_Date) VALUES (2, 'annual', '2023-05-11');
INSERT INTO subscriptions (id, type, expiration_Date) VALUES (3, 'annual', '2023-11-15');
INSERT INTO subscriptions (id, type, expiration_Date) VALUES (4, 'annual', '2024-02-01');
INSERT INTO subscriptions (id, type, expiration_Date) VALUES (5, 'annual', '2023-12-05');
INSERT INTO subscriptions (id, type, expiration_Date) VALUES (6, 'annual', '2023-12-15');

INSERT INTO subscription_status (subscription_id, status) VALUES (1, 'ACTIVE');
INSERT INTO subscription_status (subscription_id, status) VALUES (2, 'EXPIRE');
INSERT INTO subscription_status (subscription_id, status) VALUES (3, 'ACTIVE');
INSERT INTO subscription_status (subscription_id, status) VALUES (4, 'ACTIVE');
INSERT INTO subscription_status (subscription_id, status) VALUES (5, 'ACTIVE');
INSERT INTO subscription_status (subscription_id, status) VALUES (6, 'ACTIVE');




INSERT INTO orders (item_Info, date_Info, item_id, user_username)VALUES ('dress of silk', '2023-05-11',(select id from accounts where user_Info = 'User1') ,(select username from users where username = 'User1'));
INSERT INTO orders (item_Info, date_Info, item_id, user_username)VALUES ('coat of silk', '2023-05-01', (select id from accounts where user_Info = 'User2') ,(select username from users where username = 'User2'));
INSERT INTO orders (item_Info, date_Info, item_id, user_username)VALUES ('blouse of silk', '2023-05-08', (select id from accounts where user_Info = 'User3'),(select username from users where username = 'User3'));
INSERT INTO orders (item_Info, date_Info, item_id, user_username)VALUES ('bag of leather', '2023-04-07',(select id from accounts where user_Info = 'User4') ,(select username from users where username = 'User4'));
INSERT INTO orders (item_Info, date_Info, item_id, user_username)VALUES ('hat of cotton', '2023-04-11',(select id from accounts where user_Info = 'User5') ,(select username from users where username = 'User5'));
INSERT INTO orders (item_Info, date_Info, item_id, user_username)VALUES ('coat of wol', '2023-05-12',(select id from accounts where user_Info = 'User6') ,(select username from users where username = 'User6'));

