package br.com.manage.person.person.controller;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.person.service.impl.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PersonController implements PersonControllerDocs {

    private PersonService personService;

    @PostMapping("/people")
    public MessageResponseDTO createPeople(@Valid @RequestBody PersonDTO personDTO) {
        return this.personService.create(personDTO);
    }

    @GetMapping("/people")
    public List<PersonDTO> peopleAll() {
        return this.personService.findPeopleAll();
    }

    @GetMapping("/people/{id}")
    public PersonDTO onePeople(@PathVariable Long id) {
        return this.personService.findPeople(id);
    }

    @PutMapping("/people/{id}")
    public MessageResponseDTO oneUpdatePeople(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return this.personService.updatePeople(id, personDTO);
    }

    @DeleteMapping("/people/{id}")
    public MessageResponseDTO oneDeletePeople(@PathVariable Long id) {
        return this.personService.deletePeople(id);
    }
}
