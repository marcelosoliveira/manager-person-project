package br.com.manage.person.user.controller;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.user.service.impl.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @PostMapping("/users")
    @ApiOperation(value = "Register user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration ok")
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Authorization", defaultValue = "Access allowed"),
    })
    public MessageResponseDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return this.userService.createUserService(userDTO);
    }

    @GetMapping("/users")
    @ApiOperation(value = "List all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok")
    })
    public List<UserDTO> findAll() {
        return this.userService.findUsersAll();
    }
}
