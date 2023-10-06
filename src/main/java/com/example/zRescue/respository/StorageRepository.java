package com.example.zRescue.respository;

import com.example.zRescue.model.Issue;
import com.example.zRescue.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StorageRepository  extends JpaRepository<Report,Long> {

    @Transactional
    List<Report> findByRank(String rank);
}
