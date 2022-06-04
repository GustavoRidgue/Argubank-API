package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.City;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;

import java.util.List;

public interface CityRepositoryFacade {
    List<City> findAll();
    City findById(Long id) throws ResourceNotFoundException;
    List<City> findByName(String name) throws ResourceNotFoundException;
}
