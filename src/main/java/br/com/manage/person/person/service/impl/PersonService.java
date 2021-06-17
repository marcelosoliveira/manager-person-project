package br.com.manage.person.person.service.impl;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.exception.PersonNotFoundException;
import br.com.manage.person.exception.UserNameExistsException;
import br.com.manage.person.person.model.Person;
import br.com.manage.person.person.repository.PersonRepository;
import br.com.manage.person.person.service.interfaces.PersonInterface;
import br.com.manage.person.security.UserLoginSecurity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService implements PersonInterface {

    private PersonRepository personRepository;

    private UserLoginSecurity userLoginSecurity;

    private ModelMapper modelMapper;

    public MessageResponseDTO create(PersonDTO personDTO) {
        verifyPerson(personDTO.getCpf());
        LocalDate date = LocalDate.parse(personDTO.getBirthDate());

        Person person = toPerson(personDTO);
        person.setBirthDate(date);
        person.setUser(this.userLoginSecurity.getLoginUser());

        this.personRepository.save(person);
        return createMessageResponse("Successfully registered person");
    }

    public List<PersonDTO> findPeopleAll() {
        List<Person> listPerson = this.personRepository.findAll();
        return listPerson.stream()
                .map(this::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findPeople(Long id) {
        verifyPersonExist(id);
        Person person = this.personRepository.getById(id);
        PersonDTO personDTO = toPersonDTO(person);

        return personDTO;
    }

    public MessageResponseDTO updatePeople(Long id, PersonDTO personDTO) {
        verifyPersonExist(id);
        LocalDate date = LocalDate.parse(personDTO.getBirthDate());

        Person person = toPerson(personDTO);
        person.setId(id);
        person.setBirthDate(date);
        person.setUser(this.userLoginSecurity.getLoginUser());

        this.personRepository.save(person);
        return createMessageResponse("Person successfully updated ID: " + id);
    }

    public MessageResponseDTO deletePeople(Long id) {
        verifyPersonExist(id);
        this.personRepository.deleteById(id);

        return createMessageResponse("Successfully deleted person ID: " + id);
    }

    private MessageResponseDTO createMessageResponse(String message) {
        return MessageResponseDTO
                .builder()
                .message(message)
                .build();
    }


    @Override
    public Person toPerson(PersonDTO personDTO) {
        return this.modelMapper.map(personDTO, Person.class);
    }

    @Override
    public PersonDTO toPersonDTO(Person person) {
        return this.modelMapper.map(person, PersonDTO.class);
    }

    @Override
    public void verifyPerson(String person) throws UserNameExistsException {
        Optional<Person> personExists = Optional.ofNullable(this.personRepository.findByCpf(person));
        if (personExists.isPresent()) throw new UserNameExistsException("Person already registered!");
    }

    @Override
    public void verifyPersonExist(Long id) throws PersonNotFoundException {
        Optional<Person> person = this.personRepository.findById(id);
        if (!person.isPresent()) throw new PersonNotFoundException("Person is not registered!");
    }

}
