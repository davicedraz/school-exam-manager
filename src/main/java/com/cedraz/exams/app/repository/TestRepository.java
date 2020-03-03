package com.cedraz.exams.app.repository;

import com.cedraz.exams.app.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
