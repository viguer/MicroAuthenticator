# MicroAuthenticator
## Descripción

Funcionalidad para crear usuarios, manejar y controlar
la autenticación y seguridad.

## Diagrama donde se muestra el registro, el inició de sesión y acceso a recursos.

![Authenticator.png](img.png)

## Manual de Uso

### Configuración Base de datos

Si se desea usar MYSQL
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

O por el contrario usar la Base de datos integrada H2 (Recomendada para realizar los UnitTest)
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
### Diagrama tablas Base de datos

![DiagramaDB.png](img_1.png)


## Postman

[CollectionAuthenticator.postman_collection.json](..%2F..%2F..%2F..%2FDownloads%2FCollectionAuthenticator.postman_collection.json)

### Registro

Creación de Usuario exitoso

![ExtisoCrearUser.png](img_2.png)

Creación de Usuario, correo electrónico ya está registrado

![CorreoYaExiste.png](img_3.png)

Creación de Usuario, correo electrónico no cumple formato

![CorreoNoCorrecto.png](img_4.png)

Creación de Usuario, password no cumple regla

![passwordIncorrecto.png](img_5.png)

### Ingreso

Ingreso de Usuario exitoso

![IngresoCorrecto.png](img_7.png)

Ingreso de Usuario, credenciales incorrectos

![IngresoIncorrecto.png](img_6.png)

### Acceso a Recursos

Acceso con token JWT correcto

![TokenCorrecto.png](img_9.png)

Acceso con token JWT incorrecto

![TokenIncorrecto.png](img_8.png)### Ingreso


