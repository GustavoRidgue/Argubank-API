package br.com.ridgue.argubankapi.http.domain.request;

import br.com.ridgue.argubankapi.gateway.database.entity.embadded.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClientRequest {
    @NotNull @NotEmpty @Size(max = 255, message = "testando")
    private String name;
    @NotNull @NotEmpty @Size(max = 11, min = 11, message = "testando")
    private String cpf;
    @NotNull @NotEmpty @Size(max = 7, min = 7, message = "testando")
    private String rg;
    private String cnh;
    @NotNull @NotEmpty @Size(max = 11, min = 11, message = "testando")
    private String phoneNumber;
    @NotNull @NotEmpty @Email @Size(max = 255, message = "testando")
    private String email;
    @NotNull @NotEmpty @Size(max = 14, min = 6, message = "testando")
    private String password;
    @Past
    private LocalDate birthDate;
    private Address address;
}
