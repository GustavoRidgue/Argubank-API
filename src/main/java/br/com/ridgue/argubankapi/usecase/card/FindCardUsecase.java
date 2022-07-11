package br.com.ridgue.argubankapi.usecase.card;

import br.com.ridgue.argubankapi.domain.CardTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import br.com.ridgue.argubankapi.gateway.database.repository.CardRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.CardBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindCardUsecase {
    private final CardRepositoryFacade cardRepositoryFacade;
    private final CardBuilder cardBuilder;

    public Page<CardTO> findAll(Pageable pageable) {
        return cardRepositoryFacade.findAll(pageable).map(cardBuilder::build);
    }

    public CardTO findById(Long id) {
        Card card = cardRepositoryFacade.findById(id);
        return cardBuilder.build(card);
    }

    public List<CardTO> findAll(Long accountId) {
        List<Card> cards = cardRepositoryFacade.findAll(accountId);
        return cardBuilder.build(cards);
    }

    public CardTO findById(Long accountId, Long id) {
        Card card = cardRepositoryFacade.findById(accountId, id);
        return cardBuilder.build(card);
    }

    public CardTO findByNumber(Long accountId, String number) {
        Card card = cardRepositoryFacade.findByNumber(accountId, number);
        return cardBuilder.build(card);
    }
}
