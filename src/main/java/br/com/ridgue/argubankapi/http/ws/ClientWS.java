package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.http.domain.response.FindClientResponse;
import br.com.ridgue.argubankapi.usecase.FindClientUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.BaseWS.INTERNAL_MESSAGE_ERROR;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
public class ClientWS {
    private final FindClientUsecase findClientUsecase;

    @GetMapping(ROOT_API_WS_FIND_ALL_CLIENT)
    public ResponseEntity<FindClientResponse> findAll() {
        try {
            return ResponseEntity.ok(new FindClientResponse(findClientUsecase.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new FindClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CLIENT_BY_ID)
    public ResponseEntity<FindClientResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new FindClientResponse(Collections.singletonList(findClientUsecase.findById(id))));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).body(
                    new FindClientResponse("NOT_FOUND", Collections.singletonList("Resource " + id + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new FindClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CLIENT_BY_NAME)
    public ResponseEntity<FindClientResponse> findByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(new FindClientResponse(findClientUsecase.findByName(name)));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).body(
                    new FindClientResponse("NOT_FOUND", Collections.singletonList("Resource " + name + " not found")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new FindClientResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }
}
