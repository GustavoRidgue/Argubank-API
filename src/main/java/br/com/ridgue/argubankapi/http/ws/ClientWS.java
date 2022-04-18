package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.http.domain.request.ClientRequest;
import br.com.ridgue.argubankapi.http.domain.response.ClientResponse;
import br.com.ridgue.argubankapi.usecase.client.AlterClientUseCase;
import br.com.ridgue.argubankapi.usecase.client.FindClientUsecase;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.BaseWS.INTERNAL_MESSAGE_ERROR;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
public class ClientWS {
    private final FindClientUsecase findClientUsecase;
    private final AlterClientUseCase alterClientUseCase;

    @GetMapping(ROOT_API_WS_FIND_ALL_CLIENT)
    public ResponseEntity<ClientResponse> findAll() {
        try {
            return ResponseEntity.ok(new ClientResponse(findClientUsecase.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CLIENT_BY_ID)
    public ResponseEntity<ClientResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new ClientResponse(Collections.singletonList(findClientUsecase.findById(id))));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).body(
                    new ClientResponse("NOT_FOUND", Collections.singletonList("Resource " + id + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CLIENT_BY_NAME)
    public ResponseEntity<ClientResponse> findByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(new ClientResponse(findClientUsecase.findByName(name)));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).body(
                    new ClientResponse("NOT_FOUND", Collections.singletonList("Resource " + name + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @PostMapping(ROOT_API_WS_CREATE_CLIENT)
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request, UriComponentsBuilder uriComponentsBuilder) {
        try {
            ClientTO clientTO = alterClientUseCase.create(request);
            URI uri = uriComponentsBuilder.path(ROOT_API_PATH + ROOT_API_WS_FIND_CLIENT_BY_NAME).buildAndExpand(clientTO.getId()).toUri();

            return ResponseEntity.created(uri).body(new ClientResponse(Collections.singletonList(clientTO)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @PutMapping(ROOT_API_WS_UPDATE_CLIENT)
    public ResponseEntity<ClientResponse> update(@PathVariable("id") Long id, @RequestBody ClientRequest request) {
        try {
            ClientTO clientTO = alterClientUseCase.update(id, request);
            return ResponseEntity.ok(new ClientResponse(Collections.singletonList(clientTO)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(
                    new ClientResponse("NOT_FOUND", Collections.singletonList("Resource " + id + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @DeleteMapping(ROOT_API_WS_DELETE_CLIENT)
    public ResponseEntity<ClientResponse> delete(@PathVariable("id") Long id) {
        try {
            alterClientUseCase.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return ResponseEntity.status(404).body(
                    new ClientResponse("NOT_FOUND", Collections.singletonList("Resource " + id + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }
}
