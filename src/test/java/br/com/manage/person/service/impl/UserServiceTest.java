package br.com.manage.person.service.impl;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.exception.UserNameExistsException;
import br.com.manage.person.user.model.User;
import br.com.manage.person.user.repository.UserRepository;
import br.com.manage.person.user.service.impl.UserService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    void testUserDTOThenReturnSuccessSaveMessage() {
        UserDTO userDTO = createFakeUserDTO();
        User expectedSavedUser = createFakeUser();

        when(modelMapper.map(userDTO, User.class)).thenReturn(expectedSavedUser);
        when(userRepository.save(any(User.class))).thenReturn(expectedSavedUser);

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
    void testGivenNoDataThenReturnAllPeopleRegistered() {
        List<User> expectedRegisteredUser = Collections.singletonList(createFakeUser());
        User user = createFakeUser();
        UserDTO userDTO = createFakeUserDTO();

        when(userRepository.findAll()).thenReturn(expectedRegisteredUser);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        List<UserDTO> expectedPeopleDTOList = userService.findUsersAll();

        assertFalse(expectedPeopleDTOList.isEmpty());
        assertEquals(expectedPeopleDTOList.get(0).getId(), userDTO.getId());
    }
}
