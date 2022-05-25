package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Education;
import com.makhnyov.creditscoringapp.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> getAllEducations() {
        return educationRepository.findAll();
    }
}
