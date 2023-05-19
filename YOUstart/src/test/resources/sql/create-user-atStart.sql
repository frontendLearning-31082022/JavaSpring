SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM user_role;
DELETE FROM usr;

INSERT INTO usr(id,active,password,username) VALUES
(1, true, '$2a$10$Cn2EsDPTwhJSLUJRfntjTOa1PzyP0Hg4toyF9.XSWo2uL9RL/EoEm', 'p'),
(2, true, '$2a$10$e38iVX552ZOxdF6JoFTedOn7ouhV2r8.kCfNeFaEcH4f9S45w2i3.', 'o'),
(3, true, '$2a$10$63Uvr87odzCqcp1JxA86f.xM/Ws1lSjnZTMv1Lo683Lx6K0DhysFO', 'i');

INSERT INTO user_role(user_id,roles) VALUES
(1, 'USER'),(1, 'ADMIN'),
(2, 'USER');