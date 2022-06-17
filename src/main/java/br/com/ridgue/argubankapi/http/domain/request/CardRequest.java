package br.com.ridgue.argubankapi.http.domain.request;

import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardRequest {
    private Long accountId;
    private CardType type;
}
