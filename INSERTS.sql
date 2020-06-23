use recomida;
insert into usuario values
(3, 1, 180, 2800.0, "pepe@pepe", '1999-01-01', "123", 80.0, 0, 1),
(1, 0, 160, 2500.0, "marta@m.com", '1999-01-01', "123", 63.0, 1, 1),
(2, 3, 170, 3000.0, "juan@juan", '1999-01-01', "123", 84.0, 1, 0);

insert into comida values
(1, 100, "Una zanahoria rallada", "Zanahoria rallada", 5.0, 2),
(2, 900, "Tarta de acelga", "Tarta de acelga", 75.5, 1),
(3, 600, "Ensalada de choclo y tomate", "Ensalada de choclo y tomate", 35.0, 2),
(4, 800, "Pollo al horno", "Pollo al horno", 100.0, 1),
(5, 500, "Tostadas con queso crema", "Tostadas con queso crema", 40.0, 0),
(6, 50, "Sopa de zapallo", "Sopa de zapallo", 23.0, 2),
(7, 1500, "Guiso de lentejas con chinchulines", "Guiso de sobras", 140.9, 1),
(8, 120, "Licuado de frutos rojos", "Licuado Red Velvet", 30.0, 0),
(9, 1000, "Tostadas con hummus y licuado verde", "Veggie Break", 60.0, 0),
(10, 230, "Huevos y Bacon", "American Breakfast", 80.0 ,0),
(11, 150, "Tortilla verde", "Tortilla de zapallito y huevo", 54.99, 1);

Insert into restriccion values(1,"variado");
Insert into restriccion values(2,"vegetariano");
Insert into restriccion values(3,"vegano");
Insert into restriccion values(4,"celiaco");

select * from posicion;


Insert into comidas_restricciones
Values (1,2), (2,2), (3,2), (4,3), (5,2), (6,3), (7,1), (8,2), (9,2), (9, 3), (10, 1), (6,2), (11,2);

insert into pedido (id, estado, precio, usuario_id) values(7,3,28,1);
insert into pedido (id, estado, precio, usuario_id) values(8,3,30,2);
insert into pedido (id, estado, precio, usuario_id) values(3,3,380,1);
insert into pedido (id, estado, precio, usuario_id) values(4,3,28,2);
insert into pedido (id, estado, precio, usuario_id) values(5,3,30,1);
insert into pedido (id, estado, precio, usuario_id) values(6,3,380,2);
insert into pedido (id, estado, precio, usuario_id) values
(1, 3, 170, 1), (2, 3, 454, 2);

insert into pedidos_comidas (fk_pedido, fk_comida) values(7,2);
insert into pedidos_comidas (fk_pedido, fk_comida) values(8,5);
insert into pedidos_comidas (fk_pedido, fk_comida) values(3,2);
insert into pedidos_comidas (fk_pedido, fk_comida) values(4,2);
insert into pedidos_comidas (fk_pedido, fk_comida) values(5,5);
insert into pedidos_comidas (fk_pedido, fk_comida) values(6,5);
insert into pedidos_comidas values
(1, 1), (2, 6);

insert into usuarios_restricciones values 
(1, 2), (2, 2);