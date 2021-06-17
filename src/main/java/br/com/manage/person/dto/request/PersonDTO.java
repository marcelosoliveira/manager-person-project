package br.com.manage.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotBlank(message = "Field cannot be empty")
    @NotNull(message = "Field cannot be null")
    @Size(min = 3, max = 50, message = "Enter a minimum of 3 characters and a maximum of 50")
    private String firstName;

    @NotBlank(message = "Field cannot be empty")
    @NotNull(message = "Field cannot be null")
    @Size(min = 3, max = 50, message = "Enter a minimum of 3 characters and a maximum of 50")
    private String lastName;


    @NotNull(message = "Field cannot be null")
    @Positive
    private Integer age;

    @CPF
    @NotBlank(message = "Field cannot be empty")
    @NotNull(message = "Field cannot be null")
    private String cpf;

    private String birthDate;

    @Valid
    @NotEmpty(message = "Field cannot be empty")
    @NotNull(message = "Field cannot be null")
    private List<PhoneDTO> phones;

}
