package br.com.ridgue.argubankapi.http.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateClientRequest {
    private String name;
}
