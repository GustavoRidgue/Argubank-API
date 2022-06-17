package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.CardTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class CardResponse extends DefaultResponse {
    private List<CardTO> cardTOS;

    public CardResponse(String status, List<String> messages) {
        super(status, messages);
    }
}
