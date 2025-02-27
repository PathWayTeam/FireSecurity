package com.jvmdevelop.mvp.service;

import com.jvmdevelop.mvp.dto.ReportDto;
import com.jvmdevelop.mvp.model.Report;
import com.jvmdevelop.mvp.model.Waypoint;
import com.jvmdevelop.mvp.repo.ReportRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class ReportService {
    private final ReportRepo reportRepo;
    private final WaypointService waypointService;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String REPORT_CACHE_PREFIX = "report:";
    private static List<Report> reports ;

    public Report findReport(String name) {
        String cacheKey = REPORT_CACHE_PREFIX + name;
        Report reportFromCache = (Report) redisTemplate.opsForValue().get(cacheKey);
        if (reportFromCache == null) {
            Report report = reportRepo.findByName(name);
            if (report != null) {
                redisTemplate.opsForValue().set(cacheKey, report, 1, TimeUnit.HOURS);
            }
            return report;
        }
        return reportFromCache;
    }

    public List<Report> getAllReports() {
        if (reports == null) {
            reports = reportRepo.findAll();
            return reports;
        }
        return reports;
    }

    public Report add(ReportDto reportDto) {
        Waypoint waypoint;
        if (waypointService.exist(reportDto.getWaypoint().getId())) {
            waypoint = reportDto.getWaypoint();
        } else {
            waypoint = waypointService.add(reportDto.getWaypoint().toWaypoint());
        }

        Report report = Report.builder()
                .text(reportDto.getText())
                .procent(reportDto.getProcent())
                .waypoint(waypoint)
                .build();

        reportRepo.save(report);
        redisTemplate.opsForValue().set(REPORT_CACHE_PREFIX + report.getName(), report, 1, TimeUnit.HOURS);
        return report;
    }
}