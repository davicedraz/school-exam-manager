package com.cedraz.exams.app.repository;

import com.cedraz.exams.app.model.AnswerKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerKeyRepository extends JpaRepository<AnswerKey, Long> {

    AnswerKey findByTest_Id(long testId);

}
