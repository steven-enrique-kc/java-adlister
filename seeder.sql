USE adlister_db;
TRUNCATE ads;

INSERT INTO ads (user_id, title, description)
VALUES (1, 'Bowling Ball', 'Used for bowling.'),
       (2, 'Cellphone', 'Used for making calls.'),
       (1, 'Car', 'Used for driving.'),
       (2, 'Television', 'Used for watching news and entertainment.'),
       (1, 'Bed', 'Used for sleeping.'),
       (2, 'Grill', 'Used for outdoor cooking.'),
       (1, 'Computer', 'Used for computing.')
