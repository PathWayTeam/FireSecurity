package com.jvmdevelop.mvp.controller;

import com.jvmdevelop.mvp.dto.ReportDto;
import com.jvmdevelop.mvp.model.Report;
import com.jvmdevelop.mvp.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/send")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Report> send(@RequestBody ReportDto report) {
        return ResponseEntity.ok(reportService.add(report));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Report>> all(){
        return ResponseEntity.ok(reportService.getAllReports());
    }
}