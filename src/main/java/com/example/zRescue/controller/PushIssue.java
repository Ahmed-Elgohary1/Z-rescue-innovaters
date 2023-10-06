package com.example.zRescue.controller;


import com.example.zRescue.model.DataRequest;
import com.example.zRescue.model.Issue;
import com.example.zRescue.model.Report;
import com.example.zRescue.respository.StorageRepository;
import com.example.zRescue.service.MessageAdapter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:63342")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

@RequestMapping("/api/Issues/")
@AllArgsConstructor
public class PushIssue {

    @Autowired
    private StorageRepository repository;

    MessageAdapter messageAdapter=MessageAdapter.getInstance();

    @GetMapping(value = "/rank")
    public List<?> s(@RequestParam("rank") String rank){


        return repository.findByRank(rank);
    }


    @PostMapping(value = "/k")
    public ResponseEntity<?> k(@RequestParam("image")MultipartFile file){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/data")
    public ResponseEntity<String> processData(@RequestBody DataRequest dataRequest) {
        // Process the dataRequest object
        // ...
        return ResponseEntity.ok("Data processed successfully");
    }

    @PostMapping(value = "/push")
    public ResponseEntity<?> push(@RequestParam("image")MultipartFile file, @RequestParam("latitude") double latitude,
                                  @RequestParam("longitude") double longitude, @RequestParam ("timeStamp")Integer timeStamp) throws IOException {

        Issue issue=new Issue(latitude,longitude,timeStamp,file.getBytes());
         Report report=messageAdapter.createStationMessage(issue);

     //   System.out.println(report);
        repository.save(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
