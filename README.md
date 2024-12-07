# Instrucciones para Desplegar la Aplicación

Este documento proporciona instrucciones paso a paso para configurar y desplegar la API de Gestión de Franquicias en un entorno local.

---

## Prerrequisitos

Asegúrate de tener las siguientes herramientas instaladas en tu máquina:

1. **Java Development Kit (JDK):**
    - Versión: 11 o superior
    - [Descargar JDK](https://www.oracle.com/java/technologies/downloads/)

2. **Maven Build Tool:**
    - Versión: 3.6 o superior
    - [Descargar Maven](https://maven.apache.org/download.cgi)

3. **Base de Datos MongoDB:**
    - Inicia sesión en MongoDB Atlas
    - [Mongo Atlas web](https://account.mongodb.com/account/login?signedOut=true) 

4. **Git:**
    - [Descargar Git](https://git-scm.com/downloads)

5. **Postman (Opcional):**
    - Para probar los endpoints de la API.
    - [Descargar Postman](https://www.postman.com/downloads/)

---

## Pasos para Desplegar Localmente

### 1. Clonar el Repositorio

Usa el siguiente comando para clonar el repositorio en tu máquina local:

```bash
git clone <url_repositorio>
```

Navega al directorio del proyecto:

```bash
cd nequiApi
```

### 2. Configurar MongoDB

1. **Inicia sesión en MongoDB Atlas:**

2. **Configura la Base de Datos:**
   - En mongo, crea un cluster y obtén la cadena de conexión a ese cluster, se debe ver así: 
     ```properties
     mongodb+srv://<user>:<db_password>@nequiapiaccenture.knq35.mongodb.net/<db_name>?retryWrites=true&w=majority&appName=<cluster_name>
     ```
   - Agrega esta cadena a `application.properties` 

3. **Verifica la Conexión con MongoDB:**
    - Usa un cliente de MongoDB (como MongoDB Compass) para asegurarte de que la base de datos sea accesible.

### 3. Compilar el Proyecto

Compila y empaqueta la aplicación usando Maven:

```bash
mvn clean install
```

Este comando:
- Descargará las dependencias.
- Compilará el proyecto.
- Empaquetará la aplicación en un archivo `.jar` ejecutable ubicado en el directorio `target/`.

### 4. Ejecutar la Aplicación

Ejecuta el archivo `.jar` empaquetado para iniciar la aplicación:

```bash
java -jar target/NequiApiApplication-0.0.1-SNAPSHOT.jar
```

Por defecto, la aplicación se ejecuta en el puerto `8080`. Puedes acceder a la API en:

```
http://localhost:8080
```

### 5. Probar la API

Usa Postman o cualquier herramienta de prueba de API para interactuar con los endpoints. Ejemplos de endpoints:

- **Crear Franquicia:**
  ```
  POST /api/franchises
  {
      "name": "Nombre de la Franquicia",
      "ofices": []
  }
  ```

- **Agregar Oficina:**
  ```
  POST /api/franchises/{franchiseId}/ofices
  {
      "name": "Nombre de la Oficina",
      "products": []
  }
  ```

Consulta la documentación de la API para obtener una lista completa de endpoints.

---

## Problemas Comunes y Soluciones

### 1. `Port 8080 already in use`
- **Causa:** Otra aplicación está usando el puerto 8080.
- **Solución:** Cambia el puerto en `application.properties` o termina la aplicación en conflicto.

### 2. `MongoDB Connection Error`
- **Causa:** MongoDB no está en ejecución o está mal configurado.
- **Posibles soluciones:**
    - Verifica las credenciales
    - Verifica la URI de conexión en `application.properties`.

### 3. `Build Failure`
- **Causa:** Dependencias faltantes o configuraciones incorrectas.
- **Solución:**
    - Ejecuta `mvn clean install` para actualizar las dependencias.
    - Revisa los registros de errores para identificar problemas específicos.

---

## Configuración Adicional


Ejecuta la aplicación con:

```bash
java -jar target/franchise-management-api-0.0.1-SNAPSHOT.jar
```

---

## Detener la Aplicación

Para detener la aplicación, presiona `CTRL+C` en la terminal donde se está ejecutando.

---

Con esta guía, deberías poder configurar y ejecutar la API de Gestión de Franquicias localmente. Si encuentras problemas, consulta la sección `Problemas Comunes` o contacta al equipo de desarrollo.
