package br.com.ridgue.argubankapi.usecase;

import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.ClientBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindClientUsecase {
    private final ClientRepositoryFacade clientRepositoryFacade;
    private final ClientBuilder clientBuilder;

    public List<ClientTO> findAll() {
        List<Client> clients = clientRepositoryFacade.findAll();
        List<ClientTO> clientTOS = new ArrayList<>();

        for (Client client : clients) {
            ClientTO to = new ClientTO();
            to.setId(client.getId());
            to.setName(client.getName());

            clientTOS.add(to);
        }

        return clientTOS;
    }

    public ClientTO findById(Long id) throws ResourceNotFoundException {
        Client byId = clientRepositoryFacade.findById(id);
        return clientBuilder.build(byId);
    }

    public List<ClientTO> findByName(String name) throws ResourceNotFoundException {
        List<Client> clients = clientRepositoryFacade.findByName(name);
        List<ClientTO> clientTOS = new ArrayList<>();

        for (Client client : clients) {
            ClientTO to = new ClientTO();
            to.setId(client.getId());
            to.setName(client.getName());

            clientTOS.add(to);
        }

        return clientTOS;
    }
}
