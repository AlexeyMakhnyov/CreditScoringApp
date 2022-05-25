package com.makhnyov.creditscoringapp.repository;

import com.makhnyov.creditscoringapp.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditTermRepository extends JpaRepository<Term, Long> {

}
