package com.example.zRescue.respository;

import com.example.zRescue.model.Issue;
import com.example.zRescue.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageRepository  extends JpaRepository<Report,Long> {

    List<Report> findByRank(String rank);
}
