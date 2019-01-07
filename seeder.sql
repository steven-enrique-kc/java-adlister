USE adlister_db;


INSERT INTO users (username, users.email, users.password)
VALUES ('jim', 'jim@email.com', '$2a$12$R.nDWNBzDsNWxhqsiWIDGu54isnuVqb7NFcKZqZi4sCr3znlk7pmu'),
       ('mike', 'mike@email.com', '$2a$12$cMnbUEUwi.fy814v8p7lHuq5kUcWYtpee97JV1WD531nbjH8jrvaO');

INSERT INTO ads (user_id, title, description)
VALUES (1, 'Bowling Ball', 'Much Loved, now needs a new home'),
       (2, 'Cellphone', 'The face I look upon the most'),
       (1, 'Car', 'The trusty stead I use to traverse this urban jungle'),
       (2, 'Television', 'The perfect ending for each and every day'),
       (1, 'Bed', 'Were I spend 33% of my time statically'),
       (2, 'Grill', 'Free grill to good home'),
       (1, 'Computer', 'Used computer. Not using anymore. Hopefully someone can get some use out of it.');

INSERT INTO categories (category)
VALUES ('For Sale'),
       ('Electronics'),
       ('Sports Equipment'),
       ('Outdoors'),
       ('Vehicles'),
       ('Free Stuff');

INSERT INTO ads_categories (ad_id, category_id)
VALUES (1, 1),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 5),
       (4, 1),
       (4, 2),
       (5, 1),
       (6, 6),
       (7, 6);
