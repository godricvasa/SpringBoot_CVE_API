
# CVE API

## Problem Statement

To create an api to store the cve records from the cve api and create enpoints for getting a record using its ID and get the last n recently modified records.

## Tech Stack

- Language: Java
- Framework: Spring boot
- DataBase: MongoDB

## Modules

1. DataBase

- Conneted to mondob using the mongoclient 
- Fetched the data from the cve api using RestTemplate using 
  an offset parameter in the url parameter to get data from multiple pages
- From the api response,fetched the vulnerability array which contains all the cve records and added them inside MongoDB

2. Enitity

- created an entity model class for the data retrieval and mapped it to the mongodb collection.

3.  Repository

- Created a Repository interface and extended it to mongoRepository with the generic type Model class and Id as unique identifier

- Created Aggregation query for getting recent modifed records

4. Services

- Serice layer has 2 methods: findById and getRecentlyModifiedRecords 

- findById requires a String parameter which is the ID

- getRecentlyModifiedRecords requires 2 parameters : start and a limit,start is the days ago.

5. Controller

EndPoints: http://localhost:8080

- /api/{id} - retrieval of record using its ID

- /api?start={x}&end={y} - retrieval of recently modifed y records from x days ago 


## Output screenshots:

url: https://postimg.cc/gallery/gZJWgdt

#### Url contains:

- Get request for findById

- Get request for getRecentlyModifiedRecords

- Database structure
  

