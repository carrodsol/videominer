# VideoMiner
El objetivo del proyecto es desarrollar una herramienta de minería de datos que permita cargar, procesar y analizar información sobre creadores de contenido multimedia alojados en distintas plataformas.

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

## Autores

Julian Canela Ramos (julcanram@alum.us.es)

Noelia Páez Páez (noepaepae@alum.us.es)

Karim Elhaidini Lopez (karelhlop@alum.us.es)

Carlos Rodríguez Solano (carrodsol@alum.us.es)

Jesús Pérez Ortiz (jesperort@alum.us.es)