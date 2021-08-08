# spring-boot-people

La aplicacion se ejecuta en el Servidor Tomcat en el puerto 8080, se definio un archivo a travez de docker-compose en la raíz del proyecto, el cual crea un contenedo para almacenar los datos en postgresql.

# Crear contenedores docker-compose
Ejecute docker-compose up para levantar el contenedor defino anteriormente, para la base de datos del proyecto.

# Ejecutar contenedor postgres

ubicarse en la raíz de proyecto y ejecutar sudo docker-compose exec postgres bash.

y ejecutar psql -U root people o psql -U root people -W para tener acceso a la base de datos definida en el archivo docker-compose.

# Datos del para la base de datos y usuario por defecto contenedor 

POSTGRES_DB: people
POSTGRES_USER: root
POSTGRES_PASSWORD: 123456

# Rutas definidas dentro de al aplicación

http://localhost:8080/api/persons/index => ruta para obtener datos paginados de todos las personas existentes en la base de datos.

http://localhost:8080/api/persons/store => ruta para agregar una nueva persona a la base de datos.

http://localhost:8080/api/persons/show/{id} => ruta para acceder a los datos de una persona existente en al base de datos.
