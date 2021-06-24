package br.com.manage.person.person.controller;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Api(value = "Manager Person")
public interface PersonControllerDocs {

    @ApiOperation(value = "Register people")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration ok")
    })
    @ResponseStatus(HttpStatus.CREATED)
    MessageResponseDTO createPeople(PersonDTO personDTO);

    @ApiOperation(value = "List all peoples")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public List<PersonDTO> peopleAll();

    @ApiOperation(value = "Consult a person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    PersonDTO onePeople(Long id);

    @ApiOperation(value = "Update a person's data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    MessageResponseDTO oneUpdatePeople(Long id, PersonDTO personDTO);

    @ApiOperation(value = "Delete a person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public MessageResponseDTO oneDeletePeople(Long id);

}
