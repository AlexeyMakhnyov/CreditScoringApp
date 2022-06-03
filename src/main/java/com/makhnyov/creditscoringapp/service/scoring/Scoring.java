package com.makhnyov.creditscoringapp.service.scoring;

import com.makhnyov.creditscoringapp.model.Credit;
import com.makhnyov.creditscoringapp.model.Odds;
import com.makhnyov.creditscoringapp.model.Client;
import com.makhnyov.creditscoringapp.service.OddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Scoring {

    private final OddsService oddsService;

    @Autowired
    public Scoring(OddsService oddsService) {
        this.oddsService = oddsService;
    }

    //получение результата скоринга
    public boolean isApproved(Client client, Credit credit) {
        //проверка условия, что зарплата клиента превышает прожиточный минимум
        //и ежемесячный платёж по кредиту, иначе отказ
        if (client.getIncome().doubleValue() - credit.getMonthlyPayment().doubleValue() < 13000) {
            return false;
        }
        double p = scoring(client, credit);
        //проверка условия, если вероятность погажения кредита
        //больше 0.6, то кредит одобрен, иначе отказан
        if (p >= 0.6)
            return true;
        else
            return false;
    }

    //кредитный скроринг по логистической регрессии
    private double scoring(Client client, Credit credit) {
        //получение весовых коэффициентов
        Odds odds = oddsService.getOdds(1);
        //формула линейной регрессии p = k1 + k2*b1 ...
        double p = odds.getIntercept() + odds.getAge() * client.getAge() + odds.getDependents() * client.getDependents() +
                odds.getExperience() * client.getExperience() + odds.getGender() * client.getGender().getGenderLe() +
                odds.getIncome() * client.getIncome().doubleValue() + odds.getMonthlyPayment() * credit.getMonthlyPayment().doubleValue() +
                odds.getMarital() * (client.isMarital() ? 1d : 0d) + odds.getOwnHousing() * (client.isOwnHousing() ? 1d : 0d);
        //логистическая регрессия
        return 1.0 / (1.0 + Math.exp(-p));
    }
}
