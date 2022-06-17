package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.domain.CardTO;
import br.com.ridgue.argubankapi.http.domain.request.CardRequest;
import br.com.ridgue.argubankapi.http.domain.response.CardResponse;
import br.com.ridgue.argubankapi.usecase.card.AlterCardUseCase;
import br.com.ridgue.argubankapi.usecase.card.FindCardUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.BaseWS.INTERNAL_MESSAGE_ERROR;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.*;

@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
@Slf4j
public class CardWS {
    private final FindCardUsecase findCardUsecase;
    private final AlterCardUseCase alterCardUseCase;

    @GetMapping(ROOT_API_WS_FIND_ALL_CARD)
    public ResponseEntity<CardResponse> findAll() {
        try {
            return ResponseEntity.ok(new CardResponse(findCardUsecase.findAll()));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(500).body(
                    new CardResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CARD_BY_ID)
    public ResponseEntity<CardResponse> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new CardResponse(Collections.singletonList(findCardUsecase.findById(id))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new CardResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_ALL_CARD)
    public ResponseEntity<CardResponse> findAll(@PathVariable("accountId") Long accountId) {
        try {
            return ResponseEntity.ok(new CardResponse(findCardUsecase.findAll(accountId)));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(500).body(
                    new CardResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CARD_BY_ID)
    public ResponseEntity<CardResponse> findById(@PathVariable("accountId") Long accountId,
                                                 @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new CardResponse(Collections.singletonList(findCardUsecase.findById(accountId, id))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new CardResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @GetMapping(ROOT_API_WS_FIND_CARD_BY_NUMBER)
    public ResponseEntity<CardResponse> findByNumber(@PathVariable("accountId") Long accountId,
                                                     @PathVariable("number") String number) {
        try {
            return ResponseEntity.ok(new CardResponse(Collections.singletonList(findCardUsecase.findByNumber(accountId, number))));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new CardResponse("INTERNAL_SERVER_ERROR", Collections.singletonList(INTERNAL_MESSAGE_ERROR)));
        }
    }

    @PostMapping(ROOT_API_WS_CREATE_CARD)
    public ResponseEntity<CardResponse> create(@PathVariable("accountId") Long accountId,
                                               @RequestBody CardRequest request,
                                               UriComponentsBuilder uriComponentsBuilder) {
        try {
            CardTO cardTO = alterCardUseCase.create(accountId, request);
            URI uri = uriComponentsBuilder.path(ROOT_API_PATH + ROOT_API_WS_FIND_CARD_BY_ID).buildAndExpand(cardTO.getId()).toUri();
            return ResponseEntity.created(uri).body(new CardResponse(Collections.singletonList(cardTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
