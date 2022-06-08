# C22-PS180 [Skin Gorithm] - Cloud Computing

SkinGorithm API Documentation
EndPoint: https://dynamic-reef-344016.uc.r.appspot.com
Available endpoints:
1.	/Ingredient
a.	/Ingredient/InsertIngredients
-	Method: POST
-	Request Body:
```
{
    "IngredName": "name",
    "IngredFunction": "function",
    "IngredEffect": "effect"
}
```
```
IngredName as STRING:(mandatory) (UNIQUE)
IngredFunction as STRING: (mandatory)
IngredEffect as STRING: (mandatory)
```
-	Response:
```
{
    "error": "false",
    "status": "success",
    "message": "success insert data"
}
```

b.	/Ingredient/getAllIngredient
-	Method : GET
-	Request Body:
(Leave it blank)
-	Headers:
```
Authorization: Bearer Token (from Login)
```
-	Response: 
```
{
    "error": "false",
    "message": "success",
    "datalistset": [
        {
            "IngredName": "Niacinamide",
            "IngredFunction": "Mencerahkan Kulit",
            "IngredEffect": "Melembabkan Kulit",
            "createdAt": "2022-05-21T10:13:30.000Z",
            "updatedAt": "2022-05-21T10:13:30.000Z"
        },
        {
            "IngredName": "Niacinamidez",
            "IngredFunction": "Gatau",
            "IngredEffect": "Gatau jg",
            "createdAt": "2022-05-31T09:32:57.000Z",
            "updatedAt": "2022-05-31T09:32:57.000Z"
        }
    ]
}
```

c.	/Ingredient/getIngredByName
-	Method : GET
-	Request Body:
```
{
    "IngredName": "Niacinamide"
}
```
IngredName as STRING: Mandatory

-	Headers:
```
Authorization: Bearer Token (from Login)
```
-	Response: 
```
{
    "error": "false",
    "message": "success",
    "datalistset": {
        "IngredName": "Niacinamide",
        "IngredFunction": "Mencerahkan Kulit",
        "IngredEffect": "Melembabkan Kulit",
        "createdAt": "2022-05-21T10:13:30.000Z",
        "updatedAt": "2022-05-21T10:13:30.000Z"
    }
}
```

2.	/User
a.	/User/userRegister
-	Method : POST
-	Request Body:
```
{
    "Username": "username",
    "Password": "password",
    "Email": "example@gmail.com",
    "FullName": "Fullname Example",
    "ProfilePicture": "http://example.com"
}
```

Username as STRING: Mandatory, Unique
Password as STRING: Mandatory
Email as STRING: Mandatory, Unique
Fullname as STRING: Mandatory
ProfilePicture as STRING: non Mandatory

-	Response: 
```
{
    "status": "Success",
    "message": "Berhasil melakukan registrasi"
}
```

b.	/User/getAllUser
-	Method : GET
-	Request Body:
(leave it blank)
-	Response:
```
{
    "status": "success",
    "datalistset": [
        {
            "Username": "user",
            "Email": "example@gmail.com",
            "Password": "$2b$10$RM13qQU5T5Pgrg/Bi./Yy.3rE2g82mVvNmwuI1VEDGCU/edr/A/W",
            "FullName": "Fullname Example",
            "ProfilePicture": null,
            "createdAt": "2022-05-31T09:52:48.000Z",
            "updatedAt": "2022-05-31T09:52:48.000Z"
        }
    ]
}
```

c.	/User/getUserByUsername
-	Method: GET
-	Request Body:
```
{
    "Username": "username"
}
```

Username as STRING: Mandatory

-	Response:
```
{
    "status": "success",
    "datalistset": {
        "Username": "yusifaoktria",
        "Email": "yusifaos@gmail.com",
        "Password": "$2b$10$RM13qQU5T5Pgrg/Bi./Yy.3rE2g82mVvNmwuI91VEDGCU/edr/A/W",
        "FullName": "Yusifa Oktria Sageta",
        "ProfilePicture": null,
        "createdAt": "2022-05-31T09:52:48.000Z",
        "updatedAt": "2022-05-31T09:52:48.000Z"
    }
}
```

d.	/User/updateUser
-	Method: PUT
-	Header:
Authorization: Bearer Token (From Login)
-	Request Body:
```
{
    "Username": "username",
    "FullName": "changed FullName",
    "ProfilePicture": "Changed ProfilePicture"
}
```
Username: Mandatory
FullName as STRING: Kalau mau diganti jadi mandatory, kalau tidak ambil dari data yang lama
ProfilePicture as STRING: Kalau mau diganti jadi mandatory, kalau tidak ambil dari data yang lama

-	Response:
```
{
    "status": "success",
    "message": "Berhasil memperbarui data"
}
```

e.	/User/changePassword
-	Method: PUT
-	Header:
```
Authorization: Bearer Token (From Login)
```
-	Request Body:
```
{
    "Username": "username",
    "currentPassword": "oldPassword",
    "newPassword": "newPassword"
}
```
Username as STRING: Mandatory
currentPassword as STRING: Mandatory (harus sama dengan sebelumnya)
newPassword as STRING: Mandatory

-	Response
```
{
    "status": "success",
    "message": "Berhasil mengubah password"
}
```

3.	/Login
a.	/Login/userLogin
-	Method: POST
-	Request Body:
```
{
    "Username": "yusifaoktria",
    "Password": "test1234"
}
```
Username as STRING: Mandatory
Password as STRING: Mandatory

-	Response:
```
{
    "message": "Berhasil Login",
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoieXVzaWZhb2t0cmlhIiwiaWF0IjoxNjUzOTkyMzE4LCJleHAiOjE2NTM5OTQxMTh9.Rgh0P1TlMSiW4wfxz2oiu5FFZYH-ycyZOWamW5flIRM",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eXVzaWZhb2t0cmlh.wJRtSnh46_7I9t3dn6zVcQ-L0dtup5SkLHU6hXXSJQ",
    "expiredIn": "30m"
}
```

b.	/Login/refreshToken
-	Method: POST
-	Request Body:
```
{
    "Username": "username",
    "RefreshToken": "eyJhbGciOiJIUzI1NiJ9.eXVzaWZhb2t0cmlh.wJRtSnh46_a7I9t3dn6zVcQ-L0dtup5SkLHU6hXXSJQ"
}
```
Username as STRING: Mandatory
RefreshToken as STRING: Mandatory (From Login)

-	Response:
```
{
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoieXVzaWZhb2t0cmlhIiwiaWF0IjoxNjUzOTkzMTkwLCJleHAiOjE2NTM5OTQ5OTB9.HHzzRlmXkNgpj01z8k6-bw0TiaZjgKN4L5grUV56WQE",
    "expiredIn": "30m"
}
```

c.	/Login/userLogout
-	Method: DELETE
-	Request Body:
```
{
    "RefreshToken": "eyJhbGciOiJIUzI1NiJ9.dGFyaXN5YW5nYmVuZXI.1kAsAg76GAjyMcKJJRULP3R2a1ZaKQ6YPvhrgRxwZXU"
}
```
RefreshToken as STRING: Mandatory (From Login)

-	Response:
```
{
    "status": "success",
    "message": "Berhasil Logout"
}
```

4.	/Alarm
a.	/Alarm/insertAlarm
-	Method: POST
-	Header: 
```
Authorization: Bearer Token (From Login)
```
-	Request Body:
```
{
    "RoutinityName": "Memakai Skincare",
    "NotifyHour": "07:00:00",
    "Date": "31-05-2022",
    "fifteenBefore": false,
    "thirtyBefore": true,
    "repeatAlarm": 1,
    "Username": "tarisyangsalah"
}
```
Username as STRING: Mandatory (untuk check apakah user terdaftar)
RoutinityName as STRING: Mandatory
NotifyHour as TIME Format (HH:MM:SS): Mandatory
Date as DATE Format (YYYY-MM-DD): Mandatory
fifteenBefore as BOOLEAN: Mandatory
thirtyBefore as BOOLEAN: Mandatory
repeatAlarm as INTEGER: Mandatory

b.	/Alarm/getAlarmByUsername
-	Method: POST
-	Header: 
```
Authorization: Bearer Token (From Login)
```
-	Request Body:
```
{
    "Username": "username"
}
```
Username as STRING: Mandatory

-	Response:
```
[
    {
        "id": 3,
        "RoutinityName": "Memakai Skincare",
        "NotifyHour": "07:00:00",
        "Date": "2022-05-31",
        "fifteenBefore": false,
        "thirtyBefore": true,
        "repeatAlarm": 1,
        "Username": "username",
        "createdAt": "2022-05-31T08:17:52.000Z",
        "updatedAt": "2022-05-31T08:17:52.000Z"
    }
]
```


