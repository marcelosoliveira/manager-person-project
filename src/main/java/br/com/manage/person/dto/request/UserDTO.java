package br.com.manage.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @NotBlank(message = "Field cannot be null or empty")
    @Size(min = 3, max = 20, message = "Enter a minimum of 3 characters and a maximum of 20")
    private String username;

    @NotBlank(message = "Field cannot be null or empty")
    @Size(min = 8, max = 20, message = "Enter a minimum of 8 characters and a maximum of 20")
    private String password;

}
