package com.Secure.Secure.Repository;

import com.Secure.Secure.Model.Model;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CveRepository extends MongoRepository<Model,String> {
    @Aggregation(pipeline = {
            "{$sort: {lastModified: -1}}",
            "{$skip: ?0}",
            "{$limit: ?1}"
    })
    List<Model> findLastModified(int start,int limit);
}






