package com.Secure.Secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureApplication.class, args);
	}

}
/*
what i know about the assess

1.first i chose the java stack - spring boot
2.mongodb for the db cuz i thought the response is in a json format so which will be native for mongo
3.the result per page is 2000 so i used a offset parameter to get response from all pages
4.i get the response as string and convert it into  Bson format and  store the data in mongodb
5.i created a repo interface and extended it with the mongorepo
6.using the mongorepo inbuilt method findbyid i fetched the record
7.by using aggregation i sorted the documents in rev using their lastmodifieddate and returned the recent records
8.implemented both endpoints in the controller
9.checked and verified for response in the postman


 */