-- Datos iniciales para la base H2

-- Inserción de Albums
INSERT INTO albums (id, titulo, descripcion) VALUES 
(1, 'Mundial FIFA Qatar 2022', 'Colección oficial de figuritas del Mundial de Fútbol FIFA Qatar 2022'),
(2, 'Pokemon GO Serie 1', 'Primera serie de cartas Pokemon GO con criaturas populares'),
(3, 'Marvel Superhéroes', 'Colección de superhéroes del universo Marvel'),
(4, 'Dragon Ball Super', 'Personajes y momentos épicos de Dragon Ball Super');

-- Inserción de Stickers - Album 1: Mundial FIFA Qatar 2022
INSERT INTO stickers (id, nombre, stock_disponible, album_id) VALUES 
(1, 'Lionel Messi - Argentina', 100, 1),
(2, 'Kylian Mbappé - Francia', 95, 1),
(3, 'Neymar Jr - Brasil', 90, 1),
(4, 'Cristiano Ronaldo - Portugal', 85, 1),
(5, 'Escudo Argentina', 50, 1),
(6, 'Estadio Lusail', 45, 1),
(7, 'Copa del Mundo', 20, 1),
(8, 'Mascota Mundial Laeeb', 15, 1);

-- Inserción de Stickers - Album 2: Pokemon GO Serie 1
INSERT INTO stickers (id, nombre, stock_disponible, album_id) VALUES 
(9, 'Pikachu', 120, 2),
(10, 'Charmander', 110, 2),
(11, 'Squirtle', 115, 2),
(12, 'Bulbasaur', 105, 2),
(13, 'Charizard', 40, 2),
(14, 'Blastoise', 35, 2),
(15, 'Mewtwo Shiny', 10, 2),
(16, 'Mew Holográfico', 8, 2);

-- Inserción de Stickers - Album 3: Marvel Superhéroes
INSERT INTO stickers (id, nombre, stock_disponible, album_id) VALUES 
(17, 'Spider-Man', 80, 3),
(18, 'Iron Man', 75, 3),
(19, 'Hulk', 70, 3),
(20, 'Captain America', 85, 3),
(21, 'Thor con Mjolnir', 30, 3),
(22, 'Black Widow', 35, 3),
(23, 'Thanos con Gemas', 5, 3),
(24, 'Doctor Strange Místico', 7, 3);

-- Inserción de Stickers - Album 4: Dragon Ball Super
INSERT INTO stickers (id, nombre, stock_disponible, album_id) VALUES 
(25, 'Goku Base', 90, 4),
(26, 'Vegeta Saiyan', 85, 4),
(27, 'Gohan Adulto', 80, 4),
(28, 'Goku Super Saiyan Blue', 25, 4),
(29, 'Vegeta Ultra Ego', 20, 4),
(30, 'Goku Ultra Instinto', 12, 4),
(31, 'Jiren Limit Break', 8, 4);

-- Inserción de Usuarios
INSERT INTO users (id, username, email, created_at) VALUES 
(1, 'coleccionista1', 'juan.perez@email.com', '2024-01-15 10:30:00'),
(2, 'pokemon_master', 'ana.garcia@email.com', '2024-02-20 14:45:00'),
(3, 'marvel_fan', 'carlos.lopez@email.com', '2024-03-10 09:15:00'),
(4, 'dragon_ball_lover', 'maria.rodriguez@email.com', '2024-04-05 16:20:00');

-- Inserción de UserStickers (colecciones de usuarios)
INSERT INTO user_stickers (id, user_id, sticker_id, created_at) VALUES 
-- Usuario 1 - Coleccionista variado
(1, 1, 1, '2024-01-16 10:30:00'),  -- Messi
(2, 1, 9, '2024-01-18 11:00:00'),  -- Pikachu
(3, 1, 17, '2024-01-20 15:45:00'), -- Spider-Man
(4, 1, 7, '2024-01-25 20:10:00'),  -- Copa del Mundo (rara)

-- Usuario 2 - Especialista Pokemon
(5, 2, 9, '2024-02-21 09:30:00'),  -- Pikachu
(6, 2, 10, '2024-02-22 10:15:00'), -- Charmander
(7, 2, 13, '2024-02-25 14:20:00'), -- Charizard (rara)
(8, 2, 15, '2024-03-01 18:45:00'), -- Mewtwo Shiny (muy rara)

-- Usuario 3 - Fan de Marvel
(9, 3, 17, '2024-03-11 12:00:00'), -- Spider-Man
(10, 3, 18, '2024-03-12 13:30:00'), -- Iron Man
(11, 3, 21, '2024-03-15 16:45:00'), -- Thor (rara)

-- Usuario 4 - Dragon Ball collector
(12, 4, 25, '2024-04-06 10:00:00'), -- Goku Base
(13, 4, 26, '2024-04-07 11:30:00'), -- Vegeta
(14, 4, 28, '2024-04-10 14:15:00'), -- Goku SSB (rara)
(15, 4, 30, '2024-04-15 19:30:00'); -- Goku UI (muy rara)

-- Inserción de Packs (diferentes tipos de packs) 
INSERT INTO packs (id, created_at, album_id, user_id) VALUES 
(1, '2024-01-16 10:30:00', 1, 1),
(2, '2024-01-18 11:00:00', 1, 1),
(3, '2024-02-21 09:30:00', 2, 2),
(4, '2024-02-25 14:20:00', 2, 2),
(5, '2024-03-11 12:00:00', 3, 3),
(6, '2024-03-15 16:45:00', 3, 3),
(7, '2024-04-06 10:00:00', 4, 4),
(8, '2024-04-15 19:30:00', 4, 4);

-- Datos cargados exitosamente para pruebas con H2