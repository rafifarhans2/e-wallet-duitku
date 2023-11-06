# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

````json
{
  "username" : "sabkiya",
  "password" : "rahasia",
  "name" : "Rafi Farhan Sabkiya"
}
````

Response Body (Success) :

````json
{
  "data" : "OK"
}
````

Response Body (Failed) :

````json
{
  "errors" : "Username must not blank, ???"
}
````

## Login User

Endpoint : POST /api/auth/login

Request Body :

````json
{
  "username" : "sabkiya",
  "password" : "rahasia"
}

````

Response Body (Success) :

````json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 2342342344321 // milisecond
  }
}
````

Response Body (Failed, 401) :

````json
{
  "errors" : "Username or Password not valid"
}
````
## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

````json
{
  "data" : {
    "username" : "sabkiya",
    "name" : "Rafi Farhan Sabkiya"
  }
}
````

Response Body (Failed, 401) :

````json
{
  "errors" : "Unauthorized"
}
````

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

````json
{
  "name" : "Rafi Farhan", // put if only u want to update name
  "password" : "new password" // put if only u want to password name
}
````

Response Body (Success) :

````json
{
  "data" : {
    "username" : "sabkiya",
    "name" : "Rafi Farhan Sabkiya"
  }
}
````

Response Body (Failed, 401) :

````json
{
  "errors" : "Unauthorized"
}
````

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

````json
{
  "data" : "OK"
}
````