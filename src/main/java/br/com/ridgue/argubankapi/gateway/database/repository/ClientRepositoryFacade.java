package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;

import java.util.List;

public interface ClientRepositoryFacade {
    List<Client> findAll();
    Client findById(Long id) throws ResourceNotFoundException;
    List<Client> findByName(String name) throws ResourceNotFoundException;
    void save(Client client);
    void delete(Long id);
}
