package com.zup.StudyGoals.data;


import com.zup.StudyGoals.domain.Metas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetasRepository extends JpaRepository<Metas,Long> {

}
