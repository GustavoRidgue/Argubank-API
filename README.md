# Argubank-API
A java API to simulate a digital bank

Swagger: http://localhost:8080/ridgue/swagger-ui.html <br>
H2 database: http://localhost:8080/ridgue/h2-console/login.do?jsessionid=d887203e331feb8d6b822d12c364d53a

## Usage example

### 1º Create an user
```curl
curl --location 'http://localhost:8080/ridgue/api/client/create' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcmd1YmFuayBBUEkiLCJzdWIiOiIxIiwiaWF0IjoxNjcwNzgwMTM4LCJleHAiOjE2NzA3ODA3Mzh9.d0FY4d-NptPFyegiOm1ziEr2vhnWJtlY1ldElbcuOJs' \
--header 'Cookie: JSESSIONID=A7549DA85C85CC6FCF3F5F8DEEB2027D' \
--data-raw '{
    "name": "usename",
    "cpf": "12345678901",
    "rg": "123456789",
    "cnh": null,
    "phoneNumber": "11999999999",
    "email": "usermail@email.com",
    "password": "password",
    "birthDate": "2001-01-01",
    "address": {
        "cep": "53759420",
        "street": "Rua Ântonio Castro Neves",
        "number": "794",
        "neighborhood": "Vila Velha",
        "complement": "Bl 6, Ap 21",
        "city": {
            "id": 1,
            "name": "São Paulo",
            "state": {
                "id": 1,
                "name": "São Paulo",
                "Region": "SUDESTE"
            }
        }
    }
}'
```

### 2º Authenticate created user passing the email and password
```curl
curl --location 'http://localhost:8080/ridgue/api/auth' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=A7549DA85C85CC6FCF3F5F8DEEB2027D' \
--data-raw '{
    "email": "usermail@email.com",
    "password": "password"
}'
```
#### Copy the tokenTOS.token on response
```json
{
    "status": "SUCCESS",
    "totalPages": null,
    "totalElements": null,
    "messages": [],
    "tokenTOS": [
        {
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcmd1YmFuayBBUEkiLCJzdWIiOiIzIiwiaWF0IjoxNjg1NDU2NzM1LCJleHAiOjE2ODU0NTczMzV9.rZJcQrmYLwOMYhFL5rEIETz_3oudQoex0qDWmXengYI",
            "type": "Bearer",
            "expiration": "600000",
            "createdAt": "2023-05-30T11:25:35.7654782",
            "expirationAt": "2023-05-30T11:35:35.55"
        }
    ]
}
```

### 3º Create an account by the user ID and token
```
curl --location --request POST 'http://localhost:8080/ridgue/api/account/create/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcmd1YmFuayBBUEkiLCJzdWIiOiIzIiwiaWF0IjoxNjg1NDU2NzM1LCJleHAiOjE2ODU0NTczMzV9.rZJcQrmYLwOMYhFL5rEIETz_3oudQoex0qDWmXengYI' \
--header 'Cookie: JSESSIONID=A7549DA85C85CC6FCF3F5F8DEEB2027D' \
--data ''
```
