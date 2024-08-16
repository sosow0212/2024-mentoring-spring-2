[http://localhost:8080/api/users
```json

{
"userName": "jayoon",
"balance": 10000
}

```


http://localhost:8080/api/lottos/purchase

```json

{
"userId": 1,
"ticketCount": 5
}

```

http://localhost:8080/api/lottos/winningNumbers

```json

{
"winningNumbers": [1, 2, 3, 4, 5, 6]
}

```


--- GET
http://localhost:8080/api/users/1


response
```json

{
"userId": 1,
"userName": "jayoon",
"balance": 5000,
"lottoCount": 5
}
```


http://localhost:8080/api/users/allUsers
```json

[
{
"userId": 1,
"userName": "jayoon",
"balance": 5000,
"lottoCount": 5
}
]

```


http://localhost:8080/api/lottos/tickets/1
```json


[
  {
    "userId": 1,
    "userName": "seungyeop",
    "numbers": [
      [
        19,
        8,
        6,
        3,
        15,
        30
      ],
      [
        12,
        31,
        23,
        35,
        13,
        43
      ],
      [
        9,
        28,
        23,
        40,
        45,
        11
      ],
      [
        21,
        24,
        36,
        4,
        19,
        28
      ],
      [
        29,
        36,
        44,
        40,
        21,
        7
      ]
    ],
    "winnings": 150000
  },
  {
    "userId": 1,
    "userName": "seungyeop",
    "numbers": [
      [
        19,
        8,
        6,
        3,
        15,
        30
      ],
      [
        12,
        31,
        23,
        35,
        13,
        43
      ],
      [
        9,
        28,
        23,
        40,
        45,
        11
      ],
      [
        21,
        24,
        36,
        4,
        19,
        28
      ],
      [
        29,
        36,
        44,
        40,
        21,
        7
      ],
      [
        5,
        8,
        14,
        6,
        2,
        44
      ],
      [
        42,
        6,
        20,
        2,
        9,
        18
      ],
      [
        28,
        34,
        16,
        42,
        27,
        31
      ],
      [
        38,
        43,
        25,
        22,
        5,
        14
      ],
      [
        27,
        14,
        25,
        43,
        31,
        24
      ]
    ],
    "winnings": 400000
  }
]]


```
