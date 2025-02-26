package com.jvmdevelop.mvp.model;

import com.jvmdevelop.mvp.dto.WaypointDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "waypoints")
public class Waypoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @Column(nullable = false, unique = true)
    private List<Report> reports;

    @Column(nullable = false , unique = true)
    private String coordinate;

    public WaypointDto toWaypoint(){
        return new WaypointDto(this.reports , this.coordinate);
    }
}