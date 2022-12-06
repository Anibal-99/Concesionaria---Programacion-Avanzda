# Pasos para levantar el proyecto

### Utilidades necesarias previamente.

- **WSL2**: Windows subsystem for linux

- **Docker**: Dentro del WSL2 instalar docker
  
  - *para instalar*: sudo apt install docker

- **Docker-compose**: Dentro del WSL2 instalar docker-compose 
  
  - *para instalar*: sudo apt install docker-compose 

- **PostgreSQL**: dentro de windows tener instalado el motor de base datos postgreSQL

### Levantar el proyecto

1- Clonar el repositorio en el directorio deseado dentro de windows

2- Dentro de WSL moverse al directorio donde fue clonado el repositorio, para esto debera ejecutar un comando que le permitira moverse a la carpeta, una primera parte de ese comando es:

- cd /mnt/c/Users/<Nombre del usuario>/<una ves aqui, moverse al direcorio del repositorio>

- Dentro del repositorio debera ingresar a la carpera nombrada **dep**

3- Dentro de dep, debera ejecutar los siguientes comandos

- *sudo service docker start* : este comando le permitira ejecutar el servicio de docker

- *docker-compose up* : luego ejecutara este comando que le permitira levantar el docker compose que se encuentra en la carpeta de dep, permitiendo asi levantar una imagen del postgreSQL

4- Una ves realizado todo lo anterios lo que debera hacer es ingresar a una consola **cmd** de windows, dentro de ella ejecutara todo lo siguiente.

- *psql -U postgres -h 127.0.0.1 -p 5454* : este comando le permitira ingresar a la base de datos que esta corriendo en el puerto 5454, la contraseña es **postgres**

- Una ves dentro, debera ejecutar los siguientes scripts que se encuentran en el directorio **dep**:
  
  1- **\i init_cc.sql** : Este script lo que hara es crear la base de datos de la consecionaria con las tablas correspondientes
  
  2- **\c consdb** : Este comando le permitira moverse a la base de datos consdb para asi luego ejecutar los demas scripts
  
  3- **\i colores_loader.sql** : Carga los colores correspondientes.
  
  4- **\i paises_loader.sql** : Carga los paises con su correspondiente region.  

Una ves realizado todos estos pasos ya quedara el proyecto levantado para poder ser utilizado, lo unico que debera hacer cada ves que vaya a probar el proyecto, es entrar a WSL2, levantar el servicio de docker y luego correr el docker-compose up y con eso ya podra utilizar el proyecto sin ningun problema.
