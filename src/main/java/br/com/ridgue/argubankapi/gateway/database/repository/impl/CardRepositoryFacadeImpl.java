package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import br.com.ridgue.argubankapi.gateway.database.repository.CardRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CardRepositoryFacadeImpl implements CardRepositoryFacade {
    private final CardRepository cardRepository;

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public List<Card> findAll(Long accountId) {
        return cardRepository.findByAccountId(accountId);
    }

    public Card findById(Long id, Long accountId) {
        return cardRepository.findByIdAndAccountId(id, accountId).orElse(null);
    }

    public Card findByNumber(Long accountId, String number) {
        return cardRepository.findByAccountIdAndNumber(accountId, number).orElse(null);
    }

    public void save(Card card) {
        cardRepository.save(card);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
