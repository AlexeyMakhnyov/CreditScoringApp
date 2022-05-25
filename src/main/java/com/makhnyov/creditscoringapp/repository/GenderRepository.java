package com.makhnyov.creditscoringapp.repository;

import com.makhnyov.creditscoringapp.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
}
