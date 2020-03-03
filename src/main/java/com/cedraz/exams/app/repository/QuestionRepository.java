package com.cedraz.exams.app.repository;

import com.cedraz.exams.app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<Question, Long> { }
