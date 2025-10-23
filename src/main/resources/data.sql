-- Datos iniciales para la base H2

-- Inserción de Users primero (necesarios para creador_admin_id)
INSERT INTO users (id, username, email, nombre, apellido, telefono, role, hash_password, avatar_url, created_at) VALUES 
(1, 'admin_juan', 'admin@shopandpack.com', 'Juan', 'Pérez', '+549111234567', 'ADMIN', '$2a$10$hashpassword1', 'https://example.com/avatar1.jpg', '2024-01-01 10:00:00'),
(2, 'coleccionista1', 'juan.perez@email.com', 'Juan Carlos', 'González', '+549111234568', 'USER', '$2a$10$hashpassword2', 'https://example.com/avatar2.jpg', '2024-01-15 10:30:00'),
(3, 'pokemon_master', 'ana.garcia@email.com', 'Ana María', 'García', '+549111234569', 'USER', '$2a$10$hashpassword3', 'https://example.com/avatar3.jpg', '2024-02-20 14:45:00'),
(4, 'marvel_fan', 'carlos.lopez@email.com', 'Carlos', 'López', '+549111234570', 'USER', '$2a$10$hashpassword4', 'https://example.com/avatar4.jpg', '2024-03-10 09:15:00'),
(5, 'dragon_ball_lover', 'maria.rodriguez@email.com', 'María', 'Rodríguez', '+549111234571', 'USER', '$2a$10$hashpassword5', 'https://example.com/avatar5.jpg', '2024-04-05 16:20:00');

-- Inserción de Albums
INSERT INTO albums (id, titulo, descripcion, categoria, dificultad, creador_admin_id, total_figuritas, publicado, created_at) VALUES 
(1, 'Mundial FIFA Qatar 2022', 'Colección oficial de figuritas del Mundial de Fútbol FIFA Qatar 2022', 'DEPORTES', 'MEDIO', 1, 300, true, '2024-01-01 12:00:00'),
(2, 'Pokemon GO Serie 1', 'Primera serie de cartas Pokemon GO con criaturas populares', 'ENTRETENIMIENTO', 'FACIL', 1, 200, true, '2024-01-02 12:00:00'),
(3, 'Marvel Superhéroes', 'Colección de superhéroes del universo Marvel', 'ENTRETENIMIENTO', 'DIFICIL', 1, 250, true, '2024-01-03 12:00:00'),
(4, 'Dragon Ball Super', 'Personajes y momentos épicos de Dragon Ball Super', 'ANIME', 'DIFICIL', 1, 180, true, '2024-01-04 12:00:00');

-- Inserción de Stickers - Album 1: Mundial FIFA Qatar 2022
INSERT INTO stickers (id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, album_id) VALUES 
(1, 'Lionel Messi - Argentina', 001, 'RARA', 100, 100, 'https://example.com/messi.jpg', 1),
(2, 'Kylian Mbappé - Francia', 002, 'RARA', 95, 95, 'https://example.com/mbappe.jpg', 1),
(3, 'Neymar Jr - Brasil', 003, 'RARA', 90, 90, 'https://example.com/neymar.jpg', 1),
(4, 'Cristiano Ronaldo - Portugal', 004, 'RARA', 85, 85, 'https://example.com/ronaldo.jpg', 1),
(5, 'Escudo Argentina', 005, 'COMUN', 50, 50, 'https://example.com/escudo_arg.jpg', 1),
(6, 'Estadio Lusail', 006, 'COMUN', 45, 45, 'https://example.com/lusail.jpg', 1),
(7, 'Copa del Mundo', 007, 'EPICA', 20, 20, 'https://example.com/copa.jpg', 1),
(8, 'Mascota Mundial Laeeb', 008, 'EPICA', 15, 15, 'https://example.com/laeeb.jpg', 1);

-- Inserción de Stickers - Album 2: Pokemon GO Serie 1
INSERT INTO stickers (id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, album_id) VALUES 
(9, 'Pikachu', 025, 'COMUN', 120, 120, 'https://example.com/pikachu.jpg', 2),
(10, 'Charmander', 004, 'COMUN', 110, 110, 'https://example.com/charmander.jpg', 2),
(11, 'Squirtle', 007, 'COMUN', 115, 115, 'https://example.com/squirtle.jpg', 2),
(12, 'Bulbasaur', 001, 'COMUN', 105, 105, 'https://example.com/bulbasaur.jpg', 2),
(13, 'Charizard', 006, 'RARA', 40, 40, 'https://example.com/charizard.jpg', 2),
(14, 'Blastoise', 009, 'RARA', 35, 35, 'https://example.com/blastoise.jpg', 2),
(15, 'Mewtwo Shiny', 150, 'EPICA', 10, 10, 'https://example.com/mewtwo_shiny.jpg', 2),
(16, 'Mew Holográfico', 151, 'EPICA', 8, 8, 'https://example.com/mew_holo.jpg', 2);

-- Inserción de Stickers - Album 3: Marvel Superhéroes
INSERT INTO stickers (id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, album_id) VALUES 
(17, 'Spider-Man', 001, 'COMUN', 80, 80, 'https://example.com/spiderman.jpg', 3),
(18, 'Iron Man', 002, 'COMUN', 75, 75, 'https://example.com/ironman.jpg', 3),
(19, 'Hulk', 003, 'COMUN', 70, 70, 'https://example.com/hulk.jpg', 3),
(20, 'Captain America', 004, 'COMUN', 85, 85, 'https://example.com/cap_america.jpg', 3),
(21, 'Thor con Mjolnir', 005, 'RARA', 30, 30, 'https://example.com/thor.jpg', 3),
(22, 'Black Widow', 006, 'RARA', 35, 35, 'https://example.com/black_widow.jpg', 3),
(23, 'Thanos con Gemas', 007, 'EPICA', 5, 5, 'https://example.com/thanos.jpg', 3),
(24, 'Doctor Strange Místico', 008, 'EPICA', 7, 7, 'https://example.com/dr_strange.jpg', 3);

-- Inserción de Stickers - Album 4: Dragon Ball Super
INSERT INTO stickers (id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, album_id) VALUES 
(25, 'Goku Base', 001, 'COMUN', 90, 90, 'https://example.com/goku_base.jpg', 4),
(26, 'Vegeta Saiyan', 002, 'COMUN', 85, 85, 'https://example.com/vegeta_saiyan.jpg', 4),
(27, 'Gohan Adulto', 003, 'COMUN', 80, 80, 'https://example.com/gohan.jpg', 4),
(28, 'Goku Super Saiyan Blue', 004, 'RARA', 25, 25, 'https://example.com/goku_ssb.jpg', 4),
(29, 'Vegeta Ultra Ego', 005, 'RARA', 20, 20, 'https://example.com/vegeta_ue.jpg', 4),
(30, 'Goku Ultra Instinto', 006, 'EPICA', 12, 12, 'https://example.com/goku_ui.jpg', 4),
(31, 'Jiren Limit Break', 007, 'EPICA', 8, 8, 'https://example.com/jiren.jpg', 4);



-- Inserción de UserStickers (colecciones de usuarios)
INSERT INTO user_stickers (id, user_id, sticker_id, estado, created_at) VALUES 
-- Usuario 2 - Coleccionista variado
(1, 2, 1, 'EN_COLECCION', '2024-01-16 10:30:00'),  -- Messi
(2, 2, 9, 'EN_COLECCION', '2024-01-18 11:00:00'),  -- Pikachu
(3, 2, 17, 'EN_TRADE', '2024-01-20 15:45:00'), -- Spider-Man
(4, 2, 7, 'EN_COLECCION', '2024-01-25 20:10:00'),  -- Copa del Mundo (épica)

-- Usuario 3 - Especialista Pokemon
(5, 3, 9, 'EN_COLECCION', '2024-02-21 09:30:00'),  -- Pikachu
(6, 3, 10, 'EN_COLECCION', '2024-02-22 10:15:00'), -- Charmander
(7, 3, 13, 'EN_TRADE', '2024-02-25 14:20:00'), -- Charizard (rara)
(8, 3, 15, 'EN_COLECCION', '2024-03-01 18:45:00'), -- Mewtwo Shiny (épica)

-- Usuario 4 - Fan de Marvel
(9, 4, 17, 'EN_COLECCION', '2024-03-11 12:00:00'), -- Spider-Man
(10, 4, 18, 'EN_COLECCION', '2024-03-12 13:30:00'), -- Iron Man
(11, 4, 21, 'EN_TRADE', '2024-03-15 16:45:00'), -- Thor (rara)

-- Usuario 5 - Dragon Ball collector
(12, 5, 25, 'EN_COLECCION', '2024-04-06 10:00:00'), -- Goku Base
(13, 5, 26, 'EN_COLECCION', '2024-04-07 11:30:00'), -- Vegeta
(14, 5, 28, 'EN_TRADE', '2024-04-10 14:15:00'), -- Goku SSB (rara)
(15, 5, 30, 'EN_COLECCION', '2024-04-15 19:30:00'); -- Goku UI (épica)

-- Los packs se crean dinámicamente cuando los usuarios compran
-- No es necesario precargar packs de ejemplo

-- Datos cargados exitosamente para pruebas con H2