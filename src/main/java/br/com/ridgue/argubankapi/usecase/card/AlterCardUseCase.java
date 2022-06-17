package br.com.ridgue.argubankapi.usecase.card;

import br.com.ridgue.argubankapi.domain.CardTO;
import br.com.ridgue.argubankapi.exception.CardAlreadyCreatedException;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardLevel;
import br.com.ridgue.argubankapi.gateway.database.repository.AccountRepositoryFacade;
import br.com.ridgue.argubankapi.gateway.database.repository.CardRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.CardBuilder;
import br.com.ridgue.argubankapi.http.domain.request.CardRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AlterCardUseCase {
    private final AccountRepositoryFacade accountRepositoryFacade;
    private final CardRepositoryFacade cardRepositoryFacade;
    private final CardBuilder cardBuilder;

    public CardTO create(Long accountId, CardRequest request) throws CardAlreadyCreatedException, ResourceNotFoundException {
        final Account account = accountRepositoryFacade.findById(accountId);

        if (account == null)
            throw new ResourceNotFoundException("Account not found");

        if (cardRepositoryFacade.findAll(account.getId()).stream().anyMatch(card -> card.getType() == request.getType()))
            throw new CardAlreadyCreatedException("This type of card is already created");

        Card card = new Card();
        card.setAccount(account);
        card.setNumber("43254322");
        card.setType(request.getType());
        card.setLevel(CardLevel.GOLD);
        card.setActive(false);

        cardRepositoryFacade.save(card);

        return cardBuilder.build(card);
    }
}