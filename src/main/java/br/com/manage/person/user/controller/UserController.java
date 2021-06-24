package br.com.manage.person.user.controller;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.user.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController implements UserControllerDocs{

    private UserService userService;

    @PostMapping("/user")
    public MessageResponseDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return this.userService.createUserService(userDTO);
    }

    @GetMapping("/user")
    public List<UserDTO> findAll() {
        return this.userService.findUsersAll();
    }
}
