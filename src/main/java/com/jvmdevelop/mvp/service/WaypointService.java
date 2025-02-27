package com.jvmdevelop.mvp.service;

import com.jvmdevelop.mvp.dto.WaypointDto;
import com.jvmdevelop.mvp.model.Waypoint;
import com.jvmdevelop.mvp.repo.UserRepo;
import com.jvmdevelop.mvp.repo.WaypointRepo;
import com.jvmdevelop.mvp.utils.CoordinateProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class WaypointService {
    private final WaypointRepo waypointRepo;
    private final UserRepo userRepo;

    public Waypoint add(WaypointDto waypointDto){
        String coordinate = CoordinateProducer.getCoordinate(waypointDto.getAddress());
        Waypoint waypoint = Waypoint.builder()
                .reports(waypointDto.getReports())
                .coordinate(coordinate)
                .build();

       return  waypointRepo.save(waypoint);
    }

    public Waypoint find(String name){
        String coordinate = CoordinateProducer.getCoordinate(name);
        return waypointRepo.findByCoordinate(coordinate);
    }

    public boolean exist(Long id){
        return userRepo.existsById(id);
    }
    public List<Waypoint> getAll(){
        return waypointRepo.findAll();
    }

}
