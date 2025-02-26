package com.jvmdevelop.mvp.dto;

import com.jvmdevelop.mvp.model.Waypoint;
import lombok.Data;

@Data
public class ReportDto {
    private String name;
    private double procent;
    private String text;
    private Waypoint waypoint;
}
