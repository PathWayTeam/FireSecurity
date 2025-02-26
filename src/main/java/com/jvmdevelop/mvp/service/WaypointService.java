package com.jvmdevelop.mvp.service;

import com.jvmdevelop.mvp.model.Waypoint;
import com.jvmdevelop.mvp.repo.WaypointRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class WaypointService {
    private final WaypointRepo waypointRepo;

    public Waypoint add(Waypoint waypoint){
       return  waypointRepo.save(waypoint);
    }
    public Flux<Waypoint> getAll(){
        return (Flux<Waypoint>) waypointRepo.findAll();
    }

}
