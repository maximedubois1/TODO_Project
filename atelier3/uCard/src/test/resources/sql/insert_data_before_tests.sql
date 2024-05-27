-- Insert data in cards
INSERT INTO card (id, name, description, image_url, family, affinity, hp, energy, attack, defense, price, user_id)
VALUES (1, 'card1', 'description1', 'image1.jpg', 'family1', 'affinity1', 100, 100, 100, 100, 100, 1),
       (2, 'card2', 'description2', 'image2.jpg', 'family2', 'affinity2', 200, 200, 200, 200, 200, 1),
       (3, 'card3', 'description3', 'image3.jpg', 'family3', 'affinity3', 300, 300, 300, 300, 300, 2),
       (4, 'card4', 'description4', 'image4.jpg', 'family4', 'affinity4', 400, 400, 400, 400, 400, null);
