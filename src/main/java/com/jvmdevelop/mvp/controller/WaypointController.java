package com.jvmdevelop.mvp.controller;

import com.jvmdevelop.mvp.dto.ReportDto;
import com.jvmdevelop.mvp.dto.WaypointDto;
import com.jvmdevelop.mvp.model.Report;
import com.jvmdevelop.mvp.model.Waypoint;
import com.jvmdevelop.mvp.service.WaypointService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/waypoints")
public class WaypointController {
    private final WaypointService waypointService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Waypoint> add(@RequestBody WaypointDto waypoint){
        return ResponseEntity.ok(waypointService.add(waypoint));
    }

}
