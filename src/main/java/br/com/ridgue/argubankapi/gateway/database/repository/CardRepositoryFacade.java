package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.gateway.database.entity.Card;

import java.util.List;

public interface CardRepositoryFacade {
    List<Card> findAll();
    Card findById(Long id);
    List<Card> findAll(Long accountId);
    Card findById(Long accountId, Long id);
    Card findByNumber(Long accountId, String number);
    void save(Card card);
    void delete(Long id);
}
