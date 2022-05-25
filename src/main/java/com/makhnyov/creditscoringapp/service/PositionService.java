package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Position;
import com.makhnyov.creditscoringapp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPosition() {
        return positionRepository.findAll();
    }
}
