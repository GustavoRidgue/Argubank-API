package br.com.ridgue.argubankapi.usecase.client;

import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.ClientBuilder;
import br.com.ridgue.argubankapi.http.domain.request.ClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
@AllArgsConstructor
public class AlterClientUseCase {
    private final ClientRepositoryFacade clientRepositoryFacade;
    private final ClientBuilder clientBuilder;

    public ClientTO create(ClientRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Client client = new Client();
        client.setName(request.getName());
        client.setCpf(request.getCpf());
        client.setRg(request.getRg());
        client.setCnh(request.getCnh());
        client.setEmail(request.getEmail());
        client.setPassword(encoder.encode(request.getPassword()));
        client.setPhoneNumber(request.getPhoneNumber());
        client.setBirthDate(request.getBirthDate());
        client.setAge(Period.between(request.getBirthDate(), LocalDate.now()).getYears());
        client.setActive(true);
        client.setAddress(request.getAddress());

        clientRepositoryFacade.save(client);

        return clientBuilder.build(client);
    }

    public ClientTO update(Long id, ClientRequest request) throws ResourceNotFoundException {
        Client client = clientRepositoryFacade.findById(id);
        client.setName(request.getName());

        clientRepositoryFacade.save(client);

        return clientBuilder.build(client);
    }

    public void delete(Long id) {
        clientRepositoryFacade.delete(id);
    }
}