insert into usuario values
(3, 1, 180, 2800.0, "pepe@pepe", '1999-01-01', "db1a4c0c96fd1f7daf946ae3f066c3a55b339fab3043becfa479e0cd5e023cfc", 80.0, 0, 1, null),
(1, 0, 160, 2500.0, "marta@m.com", '1999-01-01', "db1a4c0c96fd1f7daf946ae3f066c3a55b339fab3043becfa479e0cd5e023cfc", 63.0, 1, 1, null),
(2, 3, 170, 3000.0, "juan@juan", '1999-01-01', "db1a4c0c96fd1f7daf946ae3f066c3a55b339fab3043becfa479e0cd5e023cfc", 84.0, 1, 0, null);

insert into tiposuscripcion values
(1, "mensual", 75.00),
(2, "semestral", 65.00),
(3, "anual", 50.00);

insert into comida values

(1, 100, 0, "Una zanahoria rallada", "Zanahoria rallada", 5.0, 2),
(2, 900, 0,"Tarta de acelga", "Tarta de acelga", 75.5, 1),
(3, 600, 0,"Ensalada de choclo y tomate", "Ensalada de choclo y tomate", 35.0, 2),
(4, 800, 0,"Pollo al horno", "Pollo al horno", 100.0, 1),
(5, 500, 0, "Tostadas con queso crema", "Tostadas con queso crema", 40.0, 0),
(6, 50, 0,"Sopa de zapallo", "Sopa de zapallo", 23.0, 2),
(7, 1500, 0,"Guiso de lentejas con chinchuusuariolines", "Guiso de sobras", 140.9, 1),
(8, 120, 0,"Licuado de frutos rojos", "Licuado Red Velvet", 30.0, 0),
(9, 1000, 0,"Tostadas con hummus y licuado verde", "Veggie Break", 60.0, 0),
(10, 230, 0,"Huevos y Bacon", "American Breakfast", 80.0 ,0),
(11, 150, 0,"Tortilla verde", "Tortilla de zapallito y huevo", 54.99, 1);

Insert into restriccion values(1,"variado");
Insert into restriccion values(2,"vegetariano");
Insert into restriccion values(3,"vegano");
Insert into restriccion values(4,"celiaco");

Insert into comidas_restricciones
Values (1,2), (2,2), (3,2), (4,3), (5,2), (6,3), (7,1), (8,2), (9,2), (9, 3), (10, 1), (6,2), (11,2);

insert into posicion values(1,"-34.671619","-58.569964","unlam");
insert into posicion values(2,"-34.652826","-58.616513","moron");
insert into posicion values(3,"-34.650284","-58.676943","ituzaingo");
insert into posicion values(4,"-34.820919","-58.466388","monte grande");
insert into posicion values(5,"-34.696553","-58.394538","lanus");
insert into posicion values(6,"-34.644338","-58.522648","liniers");


insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(1,3,28,1,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(2,3,28,2,1); 
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(3,3,28,3,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(4,3,28,4,1); 
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(5,3,28,1,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(6,3,28,1,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(8,3,28,4,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(7,3,28,3,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(9,3,28,5,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(10,3,28,2,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(11,3,28,5,1);
insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(12,3,28,6,1);

insert into pedido (id, estado, precio, ubicacionDestino_id,usuario_id) values(7,3,28,1);
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