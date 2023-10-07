# BookStore
An application to maintain inventory for a book store

# API Doc
## POST /book
### Request
```json
{
  "name": "The Lord of the Rings",
  "authorId": 1,
  "dateOfPublication": "1954-07-29",
  "genre": "Fantasy"
}
```
### Response
```json
{
  "result": true
}
```
## GET /book/list
### Request Params
| Param | Type | Description |  
| pageNo | Integer | Page number |  
| pageSize | Integer | Page size |  
| searchKey | String | Search key |  
### Response
```json
{
  "result": true,
  "data": {
    "books" : [
      {
        "name": "The Lord of the Rings",
        "authorId": 1,
        "dateOfPublication": "1954-07-29",
        "genre": "Fantasy"
      }
    ],
    "page" : {
        "pageNo": 0,
        "pageSize": 10,
        "totalPages": 1,
        "totalElements": 1
    }
  }
}
```
## POST /author
### Request
```json
{
  "firstName": "J.R.R",
  "lastName": "Martin",
  "penName": "Tolkien",
  "dateOfBirth": "1892-01-03",
  "dateOfDemise": null,
  "nationality": "United Kingdom"
}
```
### Response
```json
{
  "result": true
}
```
