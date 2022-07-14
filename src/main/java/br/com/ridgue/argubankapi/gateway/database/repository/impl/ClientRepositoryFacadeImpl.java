package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClientRepositoryFacadeImpl implements ClientRepositoryFacade {
    private final ClientRepository clientRepository;

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client findById(Long id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client " + id + " not found"));
    }

    @Override
    public List<Client> findByName(String name) throws ResourceNotFoundException {
        List<Client> byName = clientRepository.findByName(name);
        if (byName.isEmpty()) throw new ResourceNotFoundException("Client " + name + " not found");

        return byName;
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }
}
