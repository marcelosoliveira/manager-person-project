package br.com.manage.person.person.service.interfaces;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.person.model.Person;

public interface PersonInterface {

    void verifyPerson(String cpf);

    void verifyPersonExist(Long person);

}
