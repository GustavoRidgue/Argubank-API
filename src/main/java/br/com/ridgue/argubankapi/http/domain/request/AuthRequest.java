package br.com.ridgue.argubankapi.http.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AuthRequest {
    @Email @NotNull @NotBlank @Size(max = 255)
    private String email;
    @NotNull @NotBlank @Size(min = 6, max = 14)
    private String password;
}
