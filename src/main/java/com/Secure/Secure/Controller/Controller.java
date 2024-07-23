package com.Secure.Secure.Controller;

import com.Secure.Secure.Model.Model;
import com.Secure.Secure.Service.VulnerabilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {
    private final VulnerabilityService service;

    @Autowired
    public Controller(VulnerabilityService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Model>> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findId(id));
    }

    @GetMapping("getMod")
    public ResponseEntity<List<Model>> getRecent(@RequestParam int start,@RequestParam int end){
        List<Model> recent  = service.getRecentlyModifiedRecords(start,end);
        return ResponseEntity.ok(recent);
    }
}
