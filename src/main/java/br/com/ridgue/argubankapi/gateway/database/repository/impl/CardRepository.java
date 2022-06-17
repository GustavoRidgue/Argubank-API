package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByAccountId(Long accountId);

    Optional<Card> findByAccountIdAndNumber(Long accountId, String number);

    Optional<Card> findByIdAndAccountId(Long id, Long AccountId);
}
