# VideoMiner
El objetivo del proyecto es desarrollar una herramienta de minería de datos que permita cargar, procesar y analizar información sobre creadores de contenido multimedia alojados en distintas plataformas.

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

