package br.com.ridgue.argubankapi.http.domain.builder;

import br.com.ridgue.argubankapi.domain.CardTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CardBuilder {
    public CardTO build (Card card) {
        if (card == null) return null;

        CardTO to = new CardTO();
        to.setId(card.getId());
        to.setAccount(card.getAccount());
        to.setNumber(card.getNumber());
        to.setType(card.getType());
        to.setLevel(card.getLevel());
        to.setActive(card.isActive());
        return to;
    }

    public List<CardTO> build(List<Card> cards) {
        if (cards == null)
            return new ArrayList<>();

        return cards.stream().map(this::build).collect(Collectors.toList());
    }
}
