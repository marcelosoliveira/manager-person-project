package br.com.manage.person.service.impl;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.exception.UserNameExistsException;
import br.com.manage.person.mapper.PersonMapper;
import br.com.manage.person.user.model.User;
import br.com.manage.person.user.repository.UserRepository;
import br.com.manage.person.user.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static br.com.manage.person.utils.UserUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private UserService userService;

    @Test
    void testUserDTOThenReturnSuccessSaveMessage() {
        UserDTO userDTO = createFakeUserDTO();

        MessageResponseDTO successMessage = userService.createUserService(userDTO);

        assertEquals("User created successfully", successMessage.getMessage());
    }

    @Test
    void testVerifyUserNameExistException() throws UserNameExistsException{
        User user = new User();
        user.setUsername("jow");
        UserDTO userDTO = createFakeUserDTO();
        assertThrows(UserNameExistsException.class, () ->
                userNameExist(user.getUsername(), userDTO.getUsername()));
    }

    @Test
    void testGivenNoDataThenReturnAllUserRegistered() {
        List<User> expectedRegisteredUser = Collections.singletonList(createFakeUser());
        User user = createFakeUser();
        UserDTO userDTO = createFakeUserDTO();
        userDTO.setId(user.getId());

        when(userRepository.findAll()).thenReturn(expectedRegisteredUser);

        List<UserDTO> expectedPeopleDTOList = userService.findUsersAll();
        System.out.println(expectedPeopleDTOList.get(0).getId() + ", " + userDTO.getId());

        assertFalse(expectedPeopleDTOList.isEmpty());
        assertEquals(expectedPeopleDTOList.get(0).getId(), userDTO.getId());
    }
}
