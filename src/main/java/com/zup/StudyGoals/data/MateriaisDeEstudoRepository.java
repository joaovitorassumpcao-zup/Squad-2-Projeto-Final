package com.zup.StudyGoals.data;

import com.zup.StudyGoals.domain.MateriaisDeEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaisDeEstudoRepository extends JpaRepository<MateriaisDeEstudo, Long> {
}
