package br.com.manage.person.dto.request;

import br.com.manage.person.enums.PhoneEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private Long id;

    @NotNull(message = "Field cannot be null")
    @Enumerated(EnumType.STRING)
    private PhoneEnum type;

    @NotBlank(message = "Field cannot be null or empty")
    @Size(min = 13, max = 14, message = "Enter a minimum of 13 number and a maximum of 14")
    private String number;

}
