package com.makhnyov.creditscoringapp.service.scoring;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MonthlyPaymentService {

    //рассчёт ежемесячного платежа по форумлу:
    public BigDecimal getMonthlyPayment(BigDecimal amount, int term, BigDecimal rate) {
        double p = rate.doubleValue() / 100 / 12;
        double monthlyPayment = amount.doubleValue() * (p + p / (Math.pow((1 + p), term) - 1));
        return new BigDecimal(monthlyPayment).setScale(2, RoundingMode.DOWN);
    }

}
