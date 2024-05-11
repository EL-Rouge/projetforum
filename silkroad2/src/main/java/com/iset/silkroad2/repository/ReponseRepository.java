package com.iset.silkroad2.repository;

import com.iset.silkroad2.entities.Question;
import com.iset.silkroad2.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Integer> {

    Reponse findById(Long id);
    void deleteById(Long id);

    List<Reponse> findByQuestion(Question question);
}
