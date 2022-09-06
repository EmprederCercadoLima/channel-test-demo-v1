### Acerca de la funcionalidad expuesta
Esta funcionalidad muestra la lista de parametros segun el typeId enviado.

### URI de acceso a la API
| M&eacute;todo | URI          |
|---------------|--------------|
| GET          | /channel/niubiz/v1.0/demo/parameters |

### Headers Requeridos
| Header        | Ejemplo 											|
|---------------|---------------------------------------------------|
| Content-Type  | application/json 	|

### External API Error Codes

| Error         | prefix code        | description                             |
|---------------|-------------|-------------------------------------|
| SUPPORT_DEMO | DEMO-01 | API Demo Support Exception |

### Error Response

```bash
{
    "code": "",
    "message": ""
}
```

| Error         | code        | message                             |
|---------------|-------------|-------------------------------------|
| BAD_REQUEST_EXCEPTION | DEMO-00103 | General Bad Request |
| SERVER_ERROR_EXCEPTION | DEMO-00104 | General Exception |
| SERVER_ERROR_THROWABLE | DEMO-00105 | General Throwable Exception |
| INVALID_SWAGGER_NOTE | DEMO-00109 | Archivo swagger note invalido |