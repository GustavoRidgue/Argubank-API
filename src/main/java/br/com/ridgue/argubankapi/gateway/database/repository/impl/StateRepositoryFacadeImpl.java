package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.State;
import br.com.ridgue.argubankapi.gateway.database.repository.StateRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StateRepositoryFacadeImpl implements StateRepositoryFacade {
    private final StateRepository clientRepository;

    @Override
    public List<State> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public State findById(Long id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("State " + id + " not found"));
    }

    @Override
    public List<State> findByName(String name) throws ResourceNotFoundException {
        List<State> byName = clientRepository.findByName(name);
        if (byName.isEmpty()) throw new ResourceNotFoundException("State " + name + " not found");

        return byName;
    }
}
