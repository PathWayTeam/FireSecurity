package com.jvmdevelop.mvp.dto;

import com.jvmdevelop.mvp.model.Report;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class WaypointDto {
    private List<Report> reports;
    private String address;
}
