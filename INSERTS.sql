use recomida;
insert into tiposuscripcion values
(1, "mensual", 75.00),
(2, "semestral", 65.00),
(3, "anual", 50.00);

insert into suscripcion values
(1, 1, "2020-07-20", "2020-08-20", 1);

insert into usuario values
(3, 1, 180, 2800.0, "pepe@pepe.com", '1999-01-01', 500.0, "db1a4c0c96fd1f7daf946ae3f066c3a55b339fab3043becfa479e0cd5e023cfc", 80.0, 0, 1,"pepito", null),
(1, 0, 160, 2500.0, "marta@m.com", '1999-01-01', 500.0,  "db1a4c0c96fd1f7daf946ae3f066c3a55b339fab3043becfa479e0cd5e023cfc", 63.0, 1, 1,"martita", 1),
(2, 3, 170, 3000.0, "juan@juan.com", '1999-01-01', 500.0, "db1a4c0c96fd1f7daf946ae3f066c3a55b339fab3043becfa479e0cd5e023cfc", 84.0, 1, 0,"juancito", null);

insert into comida values
(1, 52, 12.26, 13, "Una zanahoria rallada", 0.31, "Zanahoria rallada", 5.0,  1.19, 2),
(2, 207,  20.71, 10,"Tarta de acelga", 11.02, "Tarta de acelga", 75.5, 7.17, 1 ),
(3, 600, 2.9, 20,"Ensalada de choclo y tomate",  5.53, "Ensalada de choclo y tomate", 35.0, 4.6, 2),
(4, 188,  0, 5,"Pollo al horno",  7.35, "Pollo al horno", 100.0, 28.69,1),
(5, 500, 9.5, 2, "Tostadas con queso crema",  10.5, "Tostadas con queso crema", 40.0,3.5, 0),
(6, 50, 5.13, 7,"Sopa de zapallo", 1.95, "Sopa de zapallo", 23.0, 1.76, 2 ),
(7, 1500, 96.13, 0,"Guiso de lentejas con chinchuusuariolines",  28.95, "Guiso de sobras", 140.9, 35.76,1),
(8, 120,  33.52, 78,"Licuado de frutos rojos",  0.30,"Licuado Red Velvet", 30.0, 0.88, 0),
(9, 1000,  10.5, 90,"Tostadas con hummus y licuado verde",   6.30,"Veggie Break", 60.0, 0.95,0),
(10, 230, 12, 55,"Huevos y Bacon",  27.8, "American Breakfast", 80.0 ,  6.88,0),
(11, 150, 10.72, 3,"Tortilla verde", 16.80, "Tortilla de zapallito y huevo", 54.99,  13.66 ,1);

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

insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(1,3, '2020-03-03',280,1,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(2,3, '2020-04-04',280,2,1); 
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(3,3, '2020-05-05',280,3,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(4,3, '2020-06-06',280,4,1); 
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(5,3, '2020-07-07',280,1,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(6,3, '2020-07-08',280,1,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(8,3, '2020-07-10',280,4,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(7,3, '2020-07-09',280,3,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(9,3, '2020-01-02',280,5,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(10,3, '2020-02-01',280,2,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(11,3, '2020-01-01',280,5,1);
insert into pedido (id, estado, fecha, precio, ubicacionDestino_id,usuario_id) values(12,3, '2020-04-01',280,6,1);

insert into pedidos_comidas (fk_pedido, fk_comida) values(7,2);
insert into pedidos_comidas (fk_pedido, fk_comida) values(8,5);
insert into pedidos_comidas (fk_pedido, fk_comida) values(3,2);
insert into pedidos_comidas (fk_pedido, fk_comida) values(4,2);
insert into pedidos_comidas (fk_pedido, fk_comida) values(5,5);
insert into pedidos_comidas (fk_pedido, fk_comida) values(6,5);
insert into pedidos_comidas (fk_pedido, fk_comida) values (1, 1), (2, 6);

insert into usuarios_restricciones values 
(1, 2), (2, 2);

insert into moldecupon values (1, 1, 50);