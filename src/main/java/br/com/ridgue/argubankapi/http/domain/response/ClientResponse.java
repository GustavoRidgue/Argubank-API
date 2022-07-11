package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.domain.ClientTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class ClientResponse extends DefaultResponse {
    private List<ClientTO> clientTOS;

    public ClientResponse(String status, String messages) {
        super(status, messages);
    }

    public ClientResponse(Integer page, Long size, List<ClientTO> tos) {
        super(page, size);
        this.clientTOS = tos;
    }
}
