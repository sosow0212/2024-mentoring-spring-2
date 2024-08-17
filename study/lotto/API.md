- POST 
http://localhost:8080/api/users
```json
{
  "userName": "seungyeop",
  "balance": 10000
}
```

- Response
```json

{
  "userId": 1,
  "userName": "seungyeop",
  "balance": 10000,
  "lottoCount": 0
}
```



--- POST
http://localhost:8080/api/lottos/buy


```json

{
"userId": "1",
"ticketCount": 2
}
```

--- POST
http://localhost:8080/api/lottos/setWinningNumbers

```json

{
  "winningNumbers": [1, 2, 3, 4, 5, 6]
}

```

---

- GET 
http://localhost:8080/api/lottos/tickets
```json


[
  {
    "userId": 1,
    "userName": "seungyeop",
    "numbers": [[1, 14, 15, 16, 32, 44], [1, 2, 6, 21, 24, 45]],
    "winnings": 0
  }
]

```

```json

[
  {
    "userId": 1,
    "userName": "seungyeop",
    "numbers": [[1, 14, 15, 16, 32, 44], [1, 2, 6, 21, 24, 45]],
    "winnings": 0
  }
]


```


- GET
http://localhost:8080/api/lottos/tickets/{userId}

```json

{
  "userId": 1,
  "userName": "seungyeop",
  "ticketCount": 2,
  "totalWinnings": 0,
  "balance": 8000
}


```



http://localhost:8080/api/users/{userId}
```json

{
  "userId": 1,
  "userName": "seungyeop",
  "ticketCount": 2,
  "totalWinnings": 0,
  "balance" : 8000
  }
```


http://localhost:8080/api/allUsers
```json

{
  "userId": 1,
  "userName": "seungyeop",
  "ticketCount": 2,
  "totalWinnings": 0
  }


```
