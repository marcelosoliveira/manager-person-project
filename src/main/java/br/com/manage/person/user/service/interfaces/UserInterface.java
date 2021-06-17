package br.com.manage.person.user.service.interfaces;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.exception.UserNameExistsException;
import br.com.manage.person.user.model.User;

public interface UserInterface {

    String encodePassword(String password);
    void verifyUsername(String username) throws UserNameExistsException;
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);

}
