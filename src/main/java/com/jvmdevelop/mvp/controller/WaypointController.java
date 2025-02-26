package com.jvmdevelop.mvp.controller;

import com.jvmdevelop.mvp.model.Waypoint;
import com.jvmdevelop.mvp.service.WaypointService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/waypoints")
public class WaypointController {
    private final WaypointService waypointService;

    @PostMapping("/add")
    public ResponseEntity<Waypoint> add(@RequestBody Waypoint waypoint){
        return ResponseEntity.ok(waypointService.add(waypoint));
    }

}
