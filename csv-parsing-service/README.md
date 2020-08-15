# CSV Parsing Service
In the first step of development this is the standalone application, having
the following task:
Read a given CSV file as input, parse it and transform it into a JSON Object,
representing the CSV file.

## Transformation Paradignm
The CSV file:
```
first_name;last_name;email;age
Hans;Mueller;mueller@mail.com;44
Lisa;Schmidt;schmidt@mail.com;29
```
is transformed into:
```javascript
[
  {
    "first_name": "Hans",
    "last_name": "Mueller",
    "email": "mueller@mail.com",
    "age": 44
  },
  {
    "first_name": "Lisa",
    "last_name": "Schmidt",
    "email": "schmidt@mail.com",
    "age": 29
  }
]
```

