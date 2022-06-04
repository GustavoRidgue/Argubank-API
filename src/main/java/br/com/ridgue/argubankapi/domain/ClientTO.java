package br.com.ridgue.argubankapi.domain;

import br.com.ridgue.argubankapi.gateway.database.entity.embadded.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClientTO {
    private Long id;
    private String name;
    private String cpf;
    private String rg;
    private String cnh;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDate birthDate;
    private Integer age;
    private boolean active;
    private Address address;
}
