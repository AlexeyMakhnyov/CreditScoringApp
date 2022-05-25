package com.makhnyov.creditscoringapp.service;

import com.makhnyov.creditscoringapp.model.Client;
import com.makhnyov.creditscoringapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public Client findClientByPassportSeriesAndPassportId(String passportSeries, String passportId) {
        return clientRepository.findByPassportSeriesAndPassportId(passportSeries,passportId);
    }

}
