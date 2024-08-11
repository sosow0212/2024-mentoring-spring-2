### 1. 사용자 등록(POST)
```
POST /api/lotto

{
    "name" : "jiwon",
    "money" : 5000 
}
```

### 2. 로또 구매(POST)
```
POST /api/lotto/buy

{
    "name" : "jiwon",
    "ticketCount" : 2
}
```

### 3. 로또 결과 이름으로 조회(GET)
```
GET /api/lotto/result?name=jiwon&winNumbers=1,2,3,4,5,6

response
[
    {
        "lottoNumber": [
            4,
            7,
            31,
            35,
            36,
            42
        ],
        "result": false
    },
    {
        "lottoNumber": [
            1,
            8,
            17,
            22,
            26,
            39
        ],
        "result": false
    }
]
```

### 4. 로또 결과 로또 id로 조회(GET)
```
GET /api/lotto/result/1?winNumbers=1,2,3,4,5,6

response
{
    "lottoNumber": [
        4,
        7,
        31,
        35,
        36,
        42
    ],
    "result": false
}
```

### 5. 로또 전체 결과 조회(GET)
```
GET /api/lotto/result/all?winNumbers=1,2,3,4,5,6

response
{
    "userResults": [
        {
        "name": "jiwon",
        "lottoCount": 2,
        "prizeMoney": 0
        }
    ]
}

```

