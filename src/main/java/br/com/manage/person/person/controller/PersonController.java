package br.com.manage.person.person.controller;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.person.service.impl.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PersonController {

    private PersonService personService;

    @PostMapping("/people")
    @ApiOperation(value = "Register people")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration ok")
    })
    public MessageResponseDTO createPeople(@Valid @RequestBody PersonDTO personDTO) {
        return this.personService.create(personDTO);
    }

    @GetMapping("/people")
    @ApiOperation(value = "List all peoples")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public List<PersonDTO> peopleAll() {
        return this.personService.findPeopleAll();
    }

    @GetMapping("/people/{id}")
    @ApiOperation(value = "Consult a person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public PersonDTO onePeople(@PathVariable Long id) {
        return this.personService.findPeople(id);
    }

    @PutMapping("/people/{id}")
    @ApiOperation(value = "Update a person's data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public MessageResponseDTO oneUpdatePeople(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return this.personService.updatePeople(id, personDTO);
    }

    @DeleteMapping("/people/{id}")
    @ApiOperation(value = "Delete a person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public MessageResponseDTO oneDeletePeople(@PathVariable Long id) {
        return this.personService.deletePeople(id);
    }
}
