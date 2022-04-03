# CHICKEN TEST

Bienvenidos!

A continuación, dejo comentarios e información necesaria para el correcto funcionamiento de la aplicación.

################################

Scripts de los Stored Procedures.

Es importante crear estos procedimientos para el correcto funcionamiento de la aplicación.

#Conteo de huevos con status "In farm"

CREATE PROCEDURE COUNT_BY_STATUS()
select count(status) status from animals where status='In farm' group by status order by count(id);

#Conteo de pollos con status "In farm"

CREATE PROCEDURE COUNT_BY_STATUSCHICK()
select count(status) status from chickens where status='In farm' group by status order by count(id);

#Conteo de ganado con status "In farm"

CREATE PROCEDURE COUNT_CATTLE()
select count(status) status from cattle where status='In farm' group by status order by count(id);

#Devuelve el último balance ingresado en la tabla de movimientos.

CREATE PROCEDURE LAST_BALANCE()
select newbalance from movements order by id DESC LIMIT 1;

#Actualiza el status de huevos a "Hatched" si corresponde y crea 1 pollo por cada huevo actualizado.

CREATE DEFINER=`root`@`localhost` PROCEDURE `SKIP_DAYS`()
BEGIN
INSERT INTO chickens (price, status, startdate)
SELECT price, status, curdate()
FROM animals
WHERE ((startdate<=curdate()-15)AND(status!='Hatched'));
SET SQL_SAFE_UPDATES = 0;
UPDATE animals set status = 'Hatched' where ((startdate<=curdate()-15));
SET SQL_SAFE_UPDATES = 1;
END

################################

Datos importantes para lanzar el proyecto y probar todas sus funciones:

Es importante hacer la carga de por lo menos 1 pollo, 1 huevo, ganado y al menos 1 movimiento a la base de datos antes de empezar a comprar y vender.

Sugiero utilizar las siguientes sentencias en MySQL Workbench:


#Insertará 1 huevo con esos valores en sus columnas correspondientes. Además la sentencia para registrar el movimiento correspondiente.

INSERT INTO animals(price,status,startdate)
values('2.99','In farm','2022-02-02');

INSERT INTO movements(name,animalid,price,transactiondate,transactiontype,newbalance)
values('Egg','1','2.99','2022-04-02','Purchase','50');

#Insertará 1 pollo con esos valores en sus columnas correspondientes.

INSERT INTO chickens(price,status,startdate)
values('2.99','In farm','2022-02-02');

INSERT INTO movements(name,animalid,price,transactiondate,transactiontype,newbalance)
values('Chicken','1','2.99','2022-04-02','Purchase','50');

#Insertará ganado con esos valores en sus columnas correspondientes. Actualmente hay un requisito de tener más de 3 para poder realizar ventas. (Valor fijado en el back)

INSERT INTO cattle(status,startdate)
values('In farm','2022-02-02');

################################
