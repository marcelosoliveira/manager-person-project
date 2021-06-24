package br.com.manage.person.utils;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.person.model.Person;
import br.com.manage.person.user.model.User;

import java.time.LocalDate;
import java.util.Collections;

import static br.com.manage.person.utils.UserUtils.createFakeUser;

public class PersonUtils {

    private static final String FIRST_NAME = "Ana Marcia";
    private static final String LAST_NAME = "Santos";
    private static final String CPF_NUMBER = "369.333.878-79";
    private static final Integer AGE = 40;
    private static final long PERSON_ID = 1L;
    public static final String BIRTH_DATE = "30-04-1959";
    public static final LocalDate BIRTH_DATE_PERSON = LocalDate.of(1959,04,30);
    public static final User USER_LOGIN = createFakeUser();

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .age(AGE)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .age(AGE)
                .birthDate(BIRTH_DATE_PERSON)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .user(USER_LOGIN)
                .build();
    }

}
