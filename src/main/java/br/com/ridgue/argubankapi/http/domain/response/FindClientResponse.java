package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.ClientTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class FindClientResponse extends DefaultResponse {
    private List<ClientTO> clientTOS;

    public FindClientResponse(String status, List<String> messages) {
        super(status, messages);
    }
}
