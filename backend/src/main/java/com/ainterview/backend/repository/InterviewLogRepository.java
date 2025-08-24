package com.ainterview.backend.repository;

import com.ainterview.backend.model.InterviewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewLogRepository extends JpaRepository<InterviewLog, Long> {
    // You can add custom query methods here later if needed
}
