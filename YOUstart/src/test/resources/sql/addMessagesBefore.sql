SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM message;

INSERT INTO message
(id,filename,tag,text,user_id) VALUES
(1,'1file', '1tag','t1',1),
(2,'1file','1tag','t1',1),
(3,'1file','1tag','t1',1),
(4,'1file','1tag','t1',1);
--ALTER sequence message_seq restart WITH 10;