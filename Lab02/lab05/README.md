# Security Server

## Descripción

## JWT

 * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzUyMjM2MzQsInVzZXJfbmFtZSI6Iml6enkiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYTlkNjQ4OWUtZmI2ZC00NGE5LTlkMzUtMThlOWYzODUxNTcyIiwiY2xpZW50X2lkIjoibXljbGllbnQiLCJzY29wZSI6WyJyZWFkIl19.ADWvi_RvL1IQz4rfduhduAWVt0aDB8LfsP6ewlTQ2sQ
 * Ingresar: https://jwt.io
 * Encode HS256
 [source,json]
 ----
 {
   "alg": "HS256",
   "typ": "JWT"
 }
 ----
 * Payload
 [source,json]
 ----
 {
   "exp": 1535223634,
   "user_name": "izzy",
   "authorities": [
     "ROLE_USER"
   ],
   "jti": "a9d6489e-fb6d-44a9-9d35-18e9f3851572",
   "client_id": "myclient",
   "scope": [
     "read"
   ]
 }
 ----



## Users:

 * **Username**: user1; **Password**: password
 * **Username**: user2; **Password**: password

## Clients:

 * **Client ID**: myclient; **Client Secret**: secret
 
## Flow:

* Client Credentials Grant: El recurso no requiere la autorización del usuario.
 
```
curl http://localhost:9999/oauth/token \
    -d"grant_type=client_credentials" \
    -H"Content-type:application/x-www-form-urlencoded; charset=utf-8" \
    -u myclient:secret
```


 * Authorization Code Grant: Aplicaciones de terceros tipos páginas web y no permitimos el acceso a los users y password.

```
http://localhost:9999/oauth/authorize?client_id=myclient&response_type=code&redirect_uri=http://localhost:9191/x
```

```
curl localhost:9999/oauth/token \
     -H"Content-type: application/x-www-form-urlencoded" \
     -d'grant_type=authorization_code&redirect_uri=http://localhost:9191/x&code=Xis1ez' \
     -u myclient:secret
```

 * Implicit Grant
 
```
http://localhost:9999/oauth/authorize?client_id=myclient&response_type=token&redirect_uri=http://localhost:9191/x
```

 * Password
 
 ```
 curl localhost:9999/oauth/token \
      -H"Content-type: application/x-www-form-urlencoded" \
      -d'grant_type=password&username=habuma&password=password' \
      -u myclient:secret
 ```