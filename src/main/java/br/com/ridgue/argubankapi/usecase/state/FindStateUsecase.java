package br.com.ridgue.argubankapi.usecase.state;

import br.com.ridgue.argubankapi.domain.StateTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.State;
import br.com.ridgue.argubankapi.gateway.database.repository.StateRepositoryFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindStateUsecase {
    private final StateRepositoryFacade stateRepositoryFacade;

    public List<StateTO> findAll() {
        List<StateTO> states = new ArrayList<>();

        stateRepositoryFacade.findAll()
            .forEach(state -> {
                StateTO to = new StateTO();
                to.setId(state.getId());
                to.setName(state.getName());
                to.setRegion(state.getRegion());

                states.add(to);
            });

        return states;
    }

    public StateTO findById(Long id) throws ResourceNotFoundException {
        State state = stateRepositoryFacade.findById(id);

        StateTO to = new StateTO();
        to.setId(state.getId());
        to.setName(state.getName());
        to.setRegion(state.getRegion());

        return to;
    }

    public List<StateTO> findByName(String name) throws ResourceNotFoundException {
        List<StateTO> states = new ArrayList<>();

        stateRepositoryFacade.findByName(name)
            .forEach(state -> {
                StateTO to = new StateTO();
                to.setId(state.getId());
                to.setName(state.getName());
                to.setRegion(state.getRegion());

                states.add(to);
            });

        return states;
    }
}
