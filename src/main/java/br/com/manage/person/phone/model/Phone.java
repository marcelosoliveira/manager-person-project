package br.com.manage.person.phone.model;

import br.com.manage.person.enums.PhoneEnum;
import br.com.manage.person.person.model.Person;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PhoneEnum type;

    @Column(nullable = false)
    private String number;

}
