package br.com.ridgue.argubankapi.usecase.account;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.exception.AlreadyHasAccountException;
import br.com.ridgue.argubankapi.exception.NotOldEnoughException;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardLevel;
import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardType;
import br.com.ridgue.argubankapi.gateway.database.repository.AccountRepositoryFacade;
import br.com.ridgue.argubankapi.gateway.database.repository.CardRepositoryFacade;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.AccountBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AlterAccountUseCase {
    private final ClientRepositoryFacade clientRepositoryFacade;
    private final AccountRepositoryFacade accountRepositoryFacade;
    private final CardRepositoryFacade cardRepositoryFacade;
    private final AccountBuilder accountBuilder;

    public AccountTO create(Long clientId) throws AlreadyHasAccountException, NotOldEnoughException, ResourceNotFoundException {
        Client client = clientRepositoryFacade.findById(clientId);

        if (accountRepositoryFacade.findByClientId(client.getId()) != null)
            throw new AlreadyHasAccountException("You already has an account registered");

        if (client.getAge() < 18)
            throw new NotOldEnoughException("You do not have age enough to create an account");

        Account account = new Account();
        account.setBank("Argubank LTDA.");
        account.setAmount(new BigDecimal("0"));
        account.setClient(client);
        account.setCards(account.getCards());
        account.setCards(null);
        account.setActive(true);

        account.setAgency(
                Integer.parseInt(String.valueOf(
                        LocalDate.now().getMonthValue()).concat("0").substring(0, 1)) +
                        Integer.parseInt(String.valueOf(
                                LocalDate.now().getDayOfMonth()).concat("0").substring(0, 1)));

        account.setNumber(
                String.valueOf(client.getId())
               .concat("0000")
               .substring(0, 4)
               .concat(String.valueOf(client.getAge() + LocalDate.now().getDayOfMonth())));

        account.setDigit(
                Integer.parseInt(
                        String.valueOf(client.getId())
                        .substring(String.valueOf(client.getId()).length() - 1)));

        accountRepositoryFacade.save(account);

        Card card = new Card();
        card.setAccount(account);
        card.setNumber("43254322");
        card.setType(CardType.DEBITO);
        card.setLevel(CardLevel.GOLD);
        card.setActive(false);

        cardRepositoryFacade.save(card);

        return accountBuilder.build(account);
    }

    public AccountTO activate(Long clientId) throws AlreadyHasAccountException, ResourceNotFoundException {
        Client client = clientRepositoryFacade.findById(clientId);
        Account account = accountRepositoryFacade.findById(client.getAccount().getId());

        if (account == null)
            throw new AlreadyHasAccountException("Account not found");

        List<Card> cards = cardRepositoryFacade.findAll(account.getId());

        account.setCards(cards);
        account.setActive(true);

        accountRepositoryFacade.save(account);

        return accountBuilder.build(account);
    }
}