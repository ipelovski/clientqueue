package com.example.clientqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientQueue {
    @Autowired
    private ClientRepository clientRepository;

    public Long insertNewClient(String name, boolean withPriority) {
        Client client = new Client(name, withPriority);
        Client savedClient = clientRepository.save(client);
        return savedClient.getId();
    }

    public Optional<Client> serveClient() {
        List<Client> clientsWithPriority = clientRepository.
            findByWithPriorityAndServedFalseOrderById(true);
        if (clientsWithPriority.size() > 0) {
            return Optional.of(clientsWithPriority.get(0));
        }
        List<Client> clientsWithoutPriority = clientRepository.
            findByWithPriorityAndServedFalseOrderById(false);
        if (clientsWithoutPriority.size() > 0) {
            return Optional.of(clientsWithoutPriority.get(0));
        } else {
            return Optional.empty();
        }
    }
}
