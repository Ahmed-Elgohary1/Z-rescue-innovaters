package com.example.zRescue.controller;


import com.example.zRescue.model.Issue;
import com.example.zRescue.model.Report;
import com.example.zRescue.respository.StorageRepository;
import com.example.zRescue.service.MessageAdapter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/Issues/")
@AllArgsConstructor
public class PushIssue {

    @Autowired
    private StorageRepository repository;

    MessageAdapter messageAdapter=MessageAdapter.getInstance();

    @GetMapping(value = "/rank/{rank}")
    public List<?> s(@PathVariable String rank){

//        Report report=new Report(1L,rank,56);
//        Report report1=new Report(2L,rank,64);
    List<Report>  list =new ArrayList<>();//=repository.findByRank(rank);
//        list.add(report);
//        list.add(report1);

        return list;
    }

    @PostMapping(value = "/push")
    public ResponseEntity<?> push(@RequestBody Issue issue){

         Report report=messageAdapter.createStationMessage(issue);



        repository.save(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
