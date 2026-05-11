# Proyecto de Integración AISS

El objetivo del proyecto es desarrollar una herramienta de minería de datos que permita cargar, procesar y analizar información sobre contenido multimedia alojado en distintas plataformas.
Se compone de tres microservicios principales:

## VideoMiner
Almacena, unifica y expone todos los datos multimedia recopilados de las distintas plataformas.
* **Operaciones HTTP soportadas:**
  * `GET`: Consultar canales, vídeos, subtítulos y comentarios.
  * `POST`: Crear nuevos registros (**protegido con API Key**).
  * `PUT`: Actualizar información existente (**protegido con API Key**).
  * `DELETE`: Eliminar registros (**protegido con API Key**).

## PeerTubeMiner
<img alt="GraphQL Logo" align="right" src="https://imgs.search.brave.com/5igeq6DBxKvmG2dbuutHEwCFaEVUQrymxMF1GirkN3o/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9hc3Nl/dHMuc3RpY2twbmcu/Y29tL2ltYWdlcy82/MjNhZmNjOTI3ZDQ5/NDZhY2VhZTJmYjku/cG5n" width="18%" />

Servicio 'Consumer' que extrae datos de PeerTube, los formatea y los envía al sistema central (**ETL**).
* **Operaciones HTTP que realiza:**
  * `GET`: Obtener datos de canales y vídeos directamente desde la API pública de PeerTube.
  * `POST`: Enviar los datos extraídos y transformados hacia la base de datos de VideoMiner.


## DailyMotionMiner
<img alt="GraphQL Logo" align="right" src="https://imgs.search.brave.com/1_bGfK70iHxzzF8eeY6OQYAj5zkgDTM7VpdgNtYkWY0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9sb2dv/ZG93bmxvYWQub3Jn/L3dwLWNvbnRlbnQv/dXBsb2Fkcy8yMDE4/LzExL2RhaWx5bW90/aW9uLWxvZ28ucG5n" width="35%" />

Servicio 'Consumer' que extrae datos de DailyMotion, los formatea y los envía al sistema central  (**ETL**).
* **Operaciones HTTP que realiza:**
  * `GET`: Recuperar información de canales, vídeos y comentarios desde la API de Dailymotion.
  * `POST`: Inyectar la información recolectada y transformada a VideoMiner.

## GraphQL 

<img alt="GraphQL Logo" align="right" src="https://graphql.org/img/logo.svg" width="15%" />

Se ha implementado un punto de acceso GraphQL para realizar consultas y operaciones sobre los datos de VideoMiner.
La API de GraphQL está disponible en `/graphql` y puedes usar una interfaz como GraphiQL activada por defecto en `/graphiql` 
donde verás todas las operaciones disponibles.

### Controladores disponibles
La implementación de GraphQL se encuentra en `aiss.videominer.controller.graphql` y expone las siguientes entidades:

- **`ChannelControllerGraphQL`**: Consultas sobre canales.
  - Queries: `channels`, `channel`, `videosByChannel`
  - Mutations: `createChannel`, `updateChannel`, `deleteChannel`, `createVideo`
- **`VideoControllerGraphQL`**: Consultas sobre videos.
  - Queries: `videos`, `video`, `commentsByVideo`, `captionsByVideo`
  - Mutations: `updateVideo`, `deleteVideo`, `createComment`, `createCaption`
- **`CaptionControllerGraphQL`**: Consultas sobre subtítulos.
  - Queries: `captions`, `caption`
  - Mutations: `updateCaption`, `deleteCaption`
- **`CommentControllerGraphQL`**: Consultas sobre comentarios.
  - Queries: `comments`, `comment`
  - Mutations: `updateComment`, `deleteComment`

### Ejemplo de uso (Query)
Puedes obtener un canal específico junto con todos sus videos, y de esos videos, el ID de sus comentarios asociados en una sola petición:

```graphql
query {
  channel(id: "80") {
    name
    description
    createdTime
    videos {
      id
      name
      releaseTime
      comments {
        id
      }
    }
  }
}
```

## API key

VideoMiner protege las operaciones de escritura con una API key configurada en `VideoMiner/src/main/resources/application.properties` (`videominer.api.key`).

### Uso

La API key se envía en la cabecera HTTP:

```http
Authorization: Bearer <API_KEY>
```

### Operaciones CRUD que la requieren

- `POST`
- `PUT`
- `DELETE`

Aplican sobre las rutas de `/videominer/**` de los controladores de `channels`, `videos`, `comments` y `captions`.
Los `GET` no requieren API key.

### Ejemplo

```bash
curl -X POST http://localhost:8080/videominer/channels \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer 123456789" \
  -d @full_channel.json
```

### Referencias en código

- `aiss.videominer.security.WebConfig#addInterceptors(...)`: aplica el interceptor a `/videominer/**`.
- `aiss.videominer.security.ApiKeyInterceptor#preHandle(...)`: valida `Authorization: Bearer <token>` y devuelve `401` si falta la clave en `POST`, `PUT` o `DELETE`.

## Despliegue en la Nube (Render)

Tanto VideoMiner como PeerTubeMiner y DailymotionMiner se encuentran desplegados en la plataforma [Render](https://render.com/).
<img src="RenderLogo.png" alt="Render logo" width="500" align="right"/>
### Arquitectura basada en Contenedores (Docker)
Para garantizar que los servicios se ejecutan correctamente en la nube, cada servicio cuenta con un `Dockerfile`.
Render detecta y construye estas imágenes de Docker automáticamente con cada **commit** en el repositorio.

Además, se utiliza una variable de entorno en PeerTubeMiner y DailyMotionMiner que sustituye `localhost` por la URI de VideoMiner.

### Persistencia y Carga Inicial de Datos (Data Seeding)
El despliegue se realiza utilizando el plan gratuito de Render. En consecuencia, las instancias se apagan tras 15min de inactividad.
Dado que VideoMiner utiliza una base de datos **H2 en memoria, todos los datos se pierden cada vez que el servidor entra en suspensión o se redespliega**.

Para mitigar esta limitación, se ha implementado un **poblado automático de datos**:

- `PeerTubeMiner/src/main/java/com/aiss/peertubeminer/InitialDataRunner.java` 
- `DailyMotionMiner/src/main/java/com/aiss/dailymotionminer/InitialDataRunner.java`

Gracias a esto, la base de datos de VideoMiner cuenta con información mínima inicial de forma automática cada vez que los sistemas se despiertan, estando lista para ser consultada inmediatamente vía REST o GraphQL.

## Pruebas Postman

En la carperta `tests` se encuentran varios archivos listos para importar como una colección de pruebas Postman:
- `VideoMiner_BasicCollection.postman_collection.json` es la colección que nos ha sido proporcionada en EV, pero modificada
para que la petición de `POST` incluya la API key como cabecera.
- `VideoMiner_CompleteAPITests.postman_collection.json` es una suite completa de pruebas que hemos creado para comprobar
el funcionamiento de los tres servicios. Empieza haciendo pruebas para DailyMotionMiner y PeerTubeMiner, y a
continuación incluye pruebas para todas las operaciones posibles en VideoMiner, haciendo uso de API key y la url del despliegue en la nube. Incluye también pruebas para casos
negativos por ejemplo un recurso no encontrado. Es posible que para el correcto funcionamiento de las pruebas haya que definir
una serie de variables de entorno en Postman:

| Variable              |                                          Valor (Nube) |                     Valor (Local) |
|:----------------------|------------------------------------------------------:|----------------------------------:|
| dailymotionBaseUrl    | https://dailymotionminer-api.onrender.com/dailymotion | http://localhost:8081/dailymotion |
| peertubeBaseUrl       |       https://peertubeminer-api.onrender.com/peertube |    http://localhost:8082/peertube |
| videominerBaseUrl     |        https://videominer-api.onrender.com/videominer |  http://localhost:8080/videominer |
| channelId             |                                                    80 |                                80 |
| videoId               |                                                 21857 |                             21857 |
| commentId             |                                                   896 |                               896 |
| commentId             |                                                   987 |                               987 |

IMPORTANTE: Si se quiere probar en local, se debe cambiar también la url de videominer a la que hacen post DailyMotionMiner y PeerTubeMiner, definidas en una variable en su `applicacion.properties`, pues el valor actual es la url de la nube.

## Autores

Julian Canela Ramos (julcanram@alum.us.es)

Noelia Páez Páez (noepaepae@alum.us.es)

Karim Elhaidini Lopez (karelhlop@alum.us.es)

Carlos Rodríguez Solano (carrodsol@alum.us.es)

Jesús Pérez Ortiz (jesperort@alum.us.es)
