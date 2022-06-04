package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.entity.State;

import java.util.List;

public interface StateRepositoryFacade {
    List<State> findAll();
    State findById(Long id) throws ResourceNotFoundException;
    List<State> findByName(String name) throws ResourceNotFoundException;
}
