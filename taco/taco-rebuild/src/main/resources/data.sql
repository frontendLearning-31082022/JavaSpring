--delete from ingredient_Ref;
--delete from taco;
--delete from taco_order;

--delete from ingredient;
insert into ingredient (id, name, type) 
                values ('FLTO', 'Flour Tortilla', '1');
insert into ingredient (id, name, type) 
                values ('COTO', 'Corn Tortilla', '1');
insert into ingredient (id, name, type) 
                values ('GRBF', 'Ground Beef', '2');
insert into ingredient (id, name, type) 
                values ('CARN', 'Carnitas', '2');
insert into ingredient (id, name, type) 
                values ('TMTO', 'Diced Tomatoes', '3');
insert into ingredient (id, name, type) 
                values ('LETC', 'Lettuce', '3');
insert into ingredient (id, name, type) 
                values ('CHED', 'Cheddar', '4');
insert into ingredient (id, name, type) 
                values ('JACK', 'Monterrey Jack', '4');
insert into ingredient (id, name, type) 
                values ('SLSA', 'Salsa', '5');
insert into ingredient (id, name, type) 
                values ('SRCR', 'Sour Cream', '5');