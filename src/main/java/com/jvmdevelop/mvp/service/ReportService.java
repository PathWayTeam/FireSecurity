package com.jvmdevelop.mvp.service;

import com.jvmdevelop.mvp.model.Report;
import com.jvmdevelop.mvp.repo.ReportRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepo reportRepo;

    public Report findReport(String name){
        return reportRepo.findByName(name);
    }
    public Flux<Report> getAllReports(){
        return (Flux) reportRepo.findAll();
    }
    public Report add(Report report){
        return reportRepo.save(report);
    }
}
