package com.makhnyov.creditscoringapp.repository;

import com.makhnyov.creditscoringapp.model.Odds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OddsRepository extends JpaRepository<Odds, Integer> {
}
