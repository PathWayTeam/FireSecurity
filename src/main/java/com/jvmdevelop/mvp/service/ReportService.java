package com.jvmdevelop.mvp.service;

import com.jvmdevelop.mvp.dto.ReportDto;
import com.jvmdevelop.mvp.model.Report;
import com.jvmdevelop.mvp.model.Waypoint;
import com.jvmdevelop.mvp.repo.ReportRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepo reportRepo;
    private final WaypointService waypointService;

    public Report findReport(String name) {
        return reportRepo.findByName(name);
    }

    public Flux<Report> getAllReports() {
        return (Flux) reportRepo.findAll();
    }

    public Report add(ReportDto reportDto) {
        if (waypointService.exist(reportDto.getWaypoint().getId())) {
            Report report = Report.builder()
                    .text(reportDto.getText())
                    .procent(reportDto.getProcent())
                    .waypoint(reportDto.getWaypoint())
                    .build();
            return report;
        } else {
            Waypoint waypoint = waypointService.add(reportDto.getWaypoint().toWaypoint());
            Report report = Report.builder()
                    .text(reportDto.getText())
                    .procent(reportDto.getProcent())
                    .waypoint(waypoint)
                    .build();
            return report;
        }
    }
}
