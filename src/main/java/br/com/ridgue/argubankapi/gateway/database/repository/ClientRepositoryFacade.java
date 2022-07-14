package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientRepositoryFacade {
    Page<Client> findAll(Pageable pageable);
    Client findById(Long id) throws ResourceNotFoundException;
    List<Client> findByName(String name) throws ResourceNotFoundException;
    void save(Client client);
    void delete(Long id);
    Client findByEmail(String email);
}
