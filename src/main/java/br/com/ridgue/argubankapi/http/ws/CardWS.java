package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.domain.CardTO;
import br.com.ridgue.argubankapi.exception.AlreadyHasAccountException;
import br.com.ridgue.argubankapi.exception.CardAlreadyCreatedException;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.http.domain.request.CardRequest;
import br.com.ridgue.argubankapi.http.domain.response.AccountResponse;
import br.com.ridgue.argubankapi.http.domain.response.CardResponse;
import br.com.ridgue.argubankapi.usecase.card.AlterCardUseCase;
import br.com.ridgue.argubankapi.usecase.card.FindCardUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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

    @GetMapping(ROOT_API_WS_ALL_CARD)
    @Cacheable(value = "findAllCard")
    public ResponseEntity<CardResponse> findByAll(
            @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        Page<CardTO> cards = findCardUsecase.findAll(pageable);

        return ResponseEntity.ok(new CardResponse(
                cards.getTotalPages(), cards.getTotalElements(), cards.getContent()));
    }

    @GetMapping(ROOT_API_WS_FIND_ALL_CARD)
    public ResponseEntity<CardResponse> findByAll(@PathVariable("id") Long accountId) {
        return ResponseEntity.ok(new CardResponse(findCardUsecase.findAll(accountId)));
    }

    @GetMapping(ROOT_API_WS_FIND_CARD_BY_ID)
    public ResponseEntity<CardResponse> findById(@PathVariable("id") Long accountId,
                                                 @PathVariable("cardId") Long id) {
        return ResponseEntity.ok(new CardResponse(Collections.singletonList(findCardUsecase.findById(accountId, id))));
    }

    @GetMapping(ROOT_API_WS_FIND_CARD_BY_NUMBER)
    public ResponseEntity<CardResponse> findByNumber(@PathVariable("id") Long accountId,
                                                     @PathVariable("number") String number) {
        return ResponseEntity.ok(new CardResponse(Collections.singletonList(findCardUsecase.findByNumber(accountId, number))));
    }

    @PostMapping(ROOT_API_WS_CREATE_CARD)
    @CacheEvict(value = "findAllCard", allEntries = true)
    public ResponseEntity<CardResponse> create(@PathVariable("id") Long accountId,
                                               @Valid @RequestBody CardRequest request,
                                               UriComponentsBuilder uriComponentsBuilder) {
        try {
            CardTO cardTO = alterCardUseCase.create(accountId, request);
            URI uri = uriComponentsBuilder.path(ROOT_API_PATH + ROOT_API_WS_FIND_CARD_BY_ID).buildAndExpand(cardTO.getId()).toUri();
            return ResponseEntity.created(uri).body(new CardResponse(Collections.singletonList(cardTO)));
        } catch (CardAlreadyCreatedException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CacheEvict(value = "findAllCard", allEntries = true)
    @PatchMapping(ROOT_API_WS_ACTIVATE_CARD)
    public ResponseEntity<CardResponse> activate(@PathVariable("id") Long id,
                                                 @PathVariable("accountId") Long accountId) {
        try {
            CardTO cardTO = alterCardUseCase.activate(id, accountId);
            return ResponseEntity.ok(new CardResponse(Collections.singletonList(cardTO)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
