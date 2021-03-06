package br.com.manage.person.user.controller;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Api(value = "Manager Person")
public interface UserControllerDocs {

    @ApiOperation(value = "Register user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration ok")
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Authorization", defaultValue = "Access allowed"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    MessageResponseDTO createUser(UserDTO userDTO);

    @ApiOperation(value = "List all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    List<UserDTO> findAll();
}
