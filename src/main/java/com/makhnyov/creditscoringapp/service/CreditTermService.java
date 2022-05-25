package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Term;
import com.makhnyov.creditscoringapp.repository.CreditTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CreditTermService {

    private final CreditTermRepository creditTermRepository;

    @Autowired
    public CreditTermService(CreditTermRepository creditTermRepository) {
        this.creditTermRepository = creditTermRepository;
    }

    public List<Term> getAllCreditTerm() {
        return creditTermRepository.findAll();
    }
}
