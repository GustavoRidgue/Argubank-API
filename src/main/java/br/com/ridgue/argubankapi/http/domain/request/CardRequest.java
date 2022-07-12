package br.com.ridgue.argubankapi.http.domain.request;

import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CardRequest {
    @NotNull @Positive
    private Long accountId;
    private CardType type;
}
