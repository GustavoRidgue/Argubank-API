package br.com.ridgue.argubankapi.usecase.client;

import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.ClientBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindClientUsecase {
    private final ClientRepositoryFacade clientRepositoryFacade;
    private final ClientBuilder clientBuilder;

    public Page<ClientTO> findAll(Pageable pageable) {
        return clientRepositoryFacade.findAll(pageable).map(clientBuilder::build);
    }

    public ClientTO findById(Long id) throws ResourceNotFoundException {
        Client client = clientRepositoryFacade.findById(id);
        return clientBuilder.build(client);
    }

    public List<ClientTO> findByName(String name) throws ResourceNotFoundException {
        List<Client> clients = clientRepositoryFacade.findByName(name);
        return clientBuilder.build(clients);
    }
}
