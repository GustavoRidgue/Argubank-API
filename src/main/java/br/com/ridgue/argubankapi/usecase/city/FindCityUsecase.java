package br.com.ridgue.argubankapi.usecase.city;

import br.com.ridgue.argubankapi.domain.CityTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.City;
import br.com.ridgue.argubankapi.gateway.database.repository.CityRepositoryFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindCityUsecase {
    private final CityRepositoryFacade cityRepositoryFacade;

    public List<CityTO> findAll() {
        List<CityTO> cities = new ArrayList<>();

        cityRepositoryFacade.findAll()
            .forEach(city -> {
                CityTO to = new CityTO();
                to.setId(city.getId());
                to.setName(city.getName());

                cities.add(to);
            });

        return cities;
    }

    public CityTO findById(Long id) throws ResourceNotFoundException {
        City city = cityRepositoryFacade.findById(id);

        CityTO to = new CityTO();
        to.setId(city.getId());
        to.setName(city.getName());

        return to;
    }

    public List<CityTO> findByName(String name) throws ResourceNotFoundException {
        List<CityTO> cities = new ArrayList<>();

        cityRepositoryFacade.findByName(name)
            .forEach(city -> {
                CityTO to = new CityTO();
                to.setId(city.getId());
                to.setName(city.getName());

                cities.add(to);
            });

        return cities;
    }
}
