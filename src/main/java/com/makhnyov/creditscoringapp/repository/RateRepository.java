package com.makhnyov.creditscoringapp.repository;

import com.makhnyov.creditscoringapp.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
