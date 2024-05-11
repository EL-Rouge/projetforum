package com.iset.silkroad2.repository;

import com.iset.silkroad2.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findById(Long id);

    void deleteById(Long id);

    List<Question> findByTitreqContaining(@Param("titreq") String titreq);

//    List<Question> getQuestions();


//    List<Question> getAllQuestions();


//    List<Question> searchByTitle(String title);
}
