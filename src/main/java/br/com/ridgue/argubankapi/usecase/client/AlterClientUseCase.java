package br.com.ridgue.argubankapi.usecase.client;

import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.ClientBuilder;
import br.com.ridgue.argubankapi.http.domain.request.CreateClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AlterClientUseCase {
    private final ClientRepositoryFacade clientRepositoryFacade;
    private final ClientBuilder clientBuilder;

    public ClientTO create(CreateClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());

        clientRepositoryFacade.create(client);

        return clientBuilder.build(client);
    }
}
