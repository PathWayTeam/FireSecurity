package com.jvmdevelop.mvp.repo;

import com.jvmdevelop.mvp.model.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaypointRepo extends JpaRepository<Waypoint , Long> {
    Waypoint findByCoordinate(String coordinate);
}
