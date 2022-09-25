
# User API
- technology : Spring boot
- Architecture : Api layer -> Service Layer -> Data Acces Layer - > Data BAse ( Firebase Firstore)
- base_url : http://localhost:8080/api/v1/users/
- exposed_base_url : https://4ff6db3a-a22c-4cbc-9724-ac3b406bedaf.mock.pstmn.io/api/v1/users
kindlynote: I used postmans' mock server feature to generate it and linked it with the flutter application
- Content-type : application/json


# GET all users
- path : `/`
- example response : 
```
{
  "data": [
    {
      "username": "JohnDoe",
      "email": "JohnDoe.w@gmail.com",
      "password": "JohnDoeJohnDoe",
      "createdDate": "2022-12-17"
    },
    {
      "username": "Johnhyy",
      "email": "johnny@gmail.com",
      "password": "123456",
      "createdDate": "1995-12-17"
    },
    {
      "username": "Williams Crusoe",
      "email": "crusoe.w@gmail.com",
      "password": "sailorman",
      "createdDate": "2022-12-17"
    }
  ],
  "message": "Success",
  "timestamp": "2022-09-25T18:40:15.089+00:00",
  "status": 200,
  "isSuccess": true
}

```

# GET user details
- path `{base_url}/user_details/{username}`
-response :
```
{
    "data": {
        "username": "JohnDoe",
        "email": "JohnDoe.w@gmail.com",
        "password": "JohnDoeJohnDoe",
        "createdDate": "2022-12-17"
    },
    "message": "Success",
    "timestamp": "2022-09-25T19:33:20.210+00:00",
    "status": 200,
    "isSuccess": true
}
```

# CREATE new users
- path `{base_url}/create/{username}`
- request body :
```
 {
            "username": "John Scoolie",
            "email": "JohnScoolie.w@apple.com",
            "password": "Scoolieapplepie",
            "createdDate": "2022-12-17"
},
```

# UPDATE user 
- path `{base_url}/update_user/{username}`
- sample response: 
```
{
      "username":"Johnhyy test n",
      "email": "johnny@gmail.com",
      "password": "123456",
      "createdDate": "1995-12-17"
}
```

# DELETE user
- path `{base_url}/delete_user/{username}`
- sample response : 

```
{
    "data": "user profile removed !",
    "message": "Success",
    "timestamp": "2022-09-25T19:23:47.348+00:00",
    "status": 200,
    "isSuccess": true
}
```

# CREATE auth credential - Firebase auth
- path `{base_url}/auth/signup`
- sample response : 
```
{
   "username":"New Boyz",    
    "email":"newboyz2@gmail.com",    
    "password":"new2022",
    "createdAt":"2022-10-10",    
    "acntDisabled":false,    
    "emailVerified":false
}
```
