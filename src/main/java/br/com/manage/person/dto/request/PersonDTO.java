package br.com.manage.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotBlank(message = "Field cannot be null or empty")
    @Size(min = 3, max = 50, message = "Enter a minimum of 3 characters and a maximum of 50")
    private String firstName;

    @NotBlank(message = "Field cannot be null or empty")
    @Size(min = 3, max = 50, message = "Enter a minimum of 3 characters and a maximum of 50")
    private String lastName;


    @NotNull(message = "Field cannot be null")
    @Positive(message = "Number cannot be negative")
    private Integer age;

    @CPF
    @NotBlank(message = "Field cannot be null or empty")
    private String cpf;

    private String birthDate;

    @Valid
    @NotEmpty(message = "Field cannot be null or empty")
    private List<PhoneDTO> phones;

}
