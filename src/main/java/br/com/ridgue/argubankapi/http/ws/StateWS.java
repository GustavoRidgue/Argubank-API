package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.http.domain.response.StateResponse;
import br.com.ridgue.argubankapi.usecase.state.FindStateUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.BaseWS.INTERNAL_MESSAGE_ERROR;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
@Slf4j
public class StateWS {
    private final FindStateUsecase findStateUsecase;

    @GetMapping(ROOT_API_WS_FIND_ALL_STATE)
    public ResponseEntity<StateResponse> findAll() {
        try {
            return ResponseEntity.ok(new StateResponse(findStateUsecase.findAll()));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(500).body(
                    new StateResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_STATE_BY_ID)
    public ResponseEntity<StateResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new StateResponse(Collections.singletonList(findStateUsecase.findById(id))));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(
                    new StateResponse("NOT_FOUND", Collections.singletonList(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new StateResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_STATE_BY_NAME)
    public ResponseEntity<StateResponse> findByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok(new StateResponse(findStateUsecase.findByName(name)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(
                    new StateResponse("NOT_FOUND", Collections.singletonList(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new StateResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }
}
