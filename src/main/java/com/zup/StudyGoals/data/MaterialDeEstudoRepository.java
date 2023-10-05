package com.zup.StudyGoals.data;

import com.zup.StudyGoals.domain.MaterialDeEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDeEstudoRepository extends JpaRepository<MaterialDeEstudo, Long> {
}
