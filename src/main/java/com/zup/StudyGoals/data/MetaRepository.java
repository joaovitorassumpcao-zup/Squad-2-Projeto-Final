package com.zup.StudyGoals.data;


import com.zup.StudyGoals.domain.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<Meta,Long> {

}
