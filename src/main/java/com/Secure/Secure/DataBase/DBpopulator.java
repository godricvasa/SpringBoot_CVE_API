package com.Secure.Secure.DataBase;


import org.bson.Document;
import org.springframework.web.client.RestTemplate;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;

public class DBpopulator {
    private static final String API_URL = "https://services.nvd.nist.gov/rest/json/cves/2.0/?resultsPerPage=2000&startIndex=";
    private static final int RESULTS_PER_PAGE = 2000;
    private static final int DELAY_MS = 10000;

    public static void main(String[] args) throws InterruptedException {

        String connectionString = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("Secure");
        MongoCollection<Document> collection = database.getCollection("CveCollection2");

        RestTemplate restTemplate = new RestTemplate();

        int startIndex = 0;

        while (true) {
            String url = API_URL + startIndex;
            String jsonResponse = restTemplate.getForObject(url, String.class);


            if (jsonResponse == null) {
                System.err.println("No response received from API");
                break;
            }

            try {
                Document bsonDocument = Document.parse(jsonResponse);

                List<Document> vulnerabilities = (List<Document>) bsonDocument.get("vulnerabilities");
                for (Document vulnerability : vulnerabilities) {
                    Document cve = vulnerability.get("cve", Document.class);
                    if (cve == null) continue;


                    System.out.println(cve.toJson());

                    String customId = cve.getString("id");
                    if (customId != null) {
                        cve.put("_id", customId);
                    }


                    String dat = cve.getString("lastModified");
                    String dat2 = cve.getString("published");
                    if (dat != null&&dat2!=null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                        LocalDateTime lastModifiedDateTime = LocalDateTime.parse(dat, formatter);
                        LocalDateTime publishedDateTime = LocalDateTime.parse(dat2,formatter);
                        Date newDate = Timestamp.valueOf(lastModifiedDateTime);
                        Date newDate2 = Timestamp.valueOf(publishedDateTime);
                        cve.put("lastModified", newDate);
                        cve.put("published",newDate2);
                    }

                    cve.remove("id");
                    collection.insertOne(cve);
                }

                startIndex += RESULTS_PER_PAGE;

            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Waiting for " + (DELAY_MS / 1000) + " seconds before next request...");
            Thread.sleep(DELAY_MS);
        }

        mongoClient.close();


    }
}


