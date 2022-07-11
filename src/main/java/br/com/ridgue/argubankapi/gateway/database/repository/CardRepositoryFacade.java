package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardRepositoryFacade {
    Page<Card> findAll(Pageable pageable);
    Card findById(Long id);
    List<Card> findAll(Long accountId);
    Card findById(Long accountId, Long id);
    Card findByNumber(Long accountId, String number);
    void save(Card card);
    void delete(Long id);
}
