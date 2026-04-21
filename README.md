--EJERCICIO 1--
Este ejercicio nos pide, en primer lugar conectarnos usando JDBC a la base de datos donde este el esquema ciclista. Despues hay
que sacar la informacion de los ciclistas usando la select con PreparedStatement , en este caso hay que cruzar tablas y para terminar ante de hacer el print de la 
info hay que filtrar estos ciclistas por la nacionalidad que el usuario escriba, para ello hemos importado el scanner. Si todo va
bien el bucle while (rs.next() sacara la informacion, usando las variables que hemos creado para guardar la info que sale de la
select, y si algo falla, el el catch manejara la excepcion.

--EJERCICIO 2--
Este ejercicio pide hacer un menu CRUD sobre los ciclistas, lo haremos con switch case. En la primera opcion, la de insertar, pido por 
teclado con el scanner importado anteslos datos del nuevo ciclista a importar, salvo el id que lo hallo de encontrar el id max de la tabla 
en ese momento y sumarle 1. Despues de esto se hace una sentencia insert con las variables que hemos llenado antes sobre el ciclista nuevo. 
En la opcion 2 podemos actualizar la edad y el equipo de un ciclista identificandolo por el id, primero llenamos las variables desde el 
teclado y despues las usamos en la setencia update "sustituyendolas" por las ? puestas.
En la opcion 3 eliminaremos un ciclista identificandolo por el id, pedido por el teclado, se eliminan su registros de la tabla 
participacion tambien para no violar la clave foranea participacion_ciclista_fk.
Todas las opciones muestran el nuevo de filas insertardas/eliminadas/actulizadas al final de cada case. Si algo no funcionase bien
el catch controlaria esa excepcion.

--EJERCICIO 3--
En este ejercicio daremos un menu de opciones para mostrar diferentes clasificaciones y estadisticas.
En todos los cases haremos la select para sacar la informacion que nos piden, cruzando tablas. Despues esos resultados los meteremos
en diferentes variables usando el resultSet para asi sacarlas por el print con el formato pedido por el enunciado.
Si algo no funcionase bien el catch final manejaria esa excepcion.
