package com.jvmdevelop.mvp.repo;

import com.jvmdevelop.mvp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepo extends JpaRepository<Report, Long> {
    Report findByName(String name);
}
