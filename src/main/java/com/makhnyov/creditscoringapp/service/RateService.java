package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Rate;
import com.makhnyov.creditscoringapp.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RateService {

    private final RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public Rate getRateById(long id) {
        Optional<Rate> optional = rateRepository.findById(id);
        Rate rate = null;
        if (optional.isPresent()) {
            rate = optional.get();
        } else {
            throw new RuntimeException("Ставка не найдена по id равному " + id);
        }
        return rate;
    }

}
