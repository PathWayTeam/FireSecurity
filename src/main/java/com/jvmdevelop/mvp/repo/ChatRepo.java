package com.jvmdevelop.mvp.repo;

import com.jvmdevelop.mvp.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat, Long> {
    List<Chat> findByIsTetATetTrueAndUsers_Id(Long userId);
}
