# spring-boot-people

La aplicacion se ejecuta en el Servidor Tomcat en el puerto 8080, se definio un archivo para docker-compose en la raíz del proyecto, para crear un contenedo para postgres sql.

#Crear contenedores docker-compose
Ejecute docker-compose build para levantar el contenedor de postgres para la base de datos del proyecto, definidos dentro del archivo docker-compose.yml

#Ejecutar contenedor postgres

ubicarse en la raíz de proyecto y ejecutar sudo docker-compose exec postgres bash.

y ejecutar psql -U root people o psql -U root people -W para tener acceso a la base de datos definida usando docker-compose

#Datos del contenedor

POSTGRES_DB: people
POSTGRES_USER: root
POSTGRES_PASSWORD: 123456
