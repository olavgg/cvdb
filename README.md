# Rest API Documentation

List all persons
```
curl -XGET http://localhost:8080/list
```

Get a single person
```
curl -XGET http://localhost:8080/get/{id}
```

Update person
```
curl \
    -H "Accept:application/json" \
    -H "Content-Type: application/json" \
    -XPOST http://localhost:8080/update \
    -d '{"id":1,"name":"Olav","title":"SQL developer","age":40,"buzzWords":["Oracle","MS SQL","SQLite","PostgreSQL"]}'
```

Delete person
```
curl -XPOST http://localhost:8080/delete -d 'id=1'
```