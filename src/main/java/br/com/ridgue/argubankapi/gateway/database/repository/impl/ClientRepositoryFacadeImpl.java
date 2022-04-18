package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClientRepositoryFacadeImpl implements ClientRepositoryFacade {
    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test " + id + " not found"));
    }

    @Override
    public List<Client> findByName(String name) throws ResourceNotFoundException {
        List<Client> byName = clientRepository.findByName(name);
        if (byName.isEmpty()) throw new ResourceNotFoundException("Test " + name + " not found");

        return byName;
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }
}
