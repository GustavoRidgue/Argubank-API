package br.com.ridgue.argubankapi.http.domain.builder;

import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientBuilder {
    public ClientTO build (Client client) {
        if (client == null) return null;

        ClientTO to = new ClientTO();
        to.setId(client.getId());
        to.setName(client.getName());
        return to;
    }

    public List<ClientTO> build(List<Client> clients) {
        if (clients == null)
            return new ArrayList<>();

        return clients.stream().map(this::build).collect(Collectors.toList());
    }
}
