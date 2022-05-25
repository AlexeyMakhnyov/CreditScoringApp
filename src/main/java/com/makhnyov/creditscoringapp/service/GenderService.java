package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Gender;
import com.makhnyov.creditscoringapp.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenderService {

    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }


    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

}
