package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.City;
import br.com.ridgue.argubankapi.gateway.database.repository.CityRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CityRepositoryFacadeImpl implements CityRepositoryFacade {
    private final CityRepository clientRepository;

    @Override
    public List<City> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public City findById(Long id) throws ResourceNotFoundException {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("City " + id + " not found"));
    }

    @Override
    public List<City> findByName(String name) throws ResourceNotFoundException {
        List<City> byName = clientRepository.findByName(name);
        if (byName.isEmpty()) throw new ResourceNotFoundException("City " + name + " not found");

        return byName;
    }
}
