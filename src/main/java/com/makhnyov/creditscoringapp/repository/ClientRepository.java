package com.makhnyov.creditscoringapp.repository;

import com.makhnyov.creditscoringapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByPassportSeriesAndPassportId(String passportSeries, String passportID);

}
