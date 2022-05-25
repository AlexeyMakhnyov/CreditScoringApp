package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Odds;
import com.makhnyov.creditscoringapp.repository.OddsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OddsService {

    private final OddsRepository oddsRepository;

    @Autowired
    public OddsService(OddsRepository oddsRepository) {
        this.oddsRepository = oddsRepository;
    }

    public Odds getOdds(int id) {
        Optional<Odds> optional = oddsRepository.findById(id);
        Odds odds = null;
        if (optional.isPresent()) {
            odds = optional.get();
        } else {
            throw new RuntimeException("Коэффициенты не найдены по id равному " + id);
        }
        return odds;
    }
}
