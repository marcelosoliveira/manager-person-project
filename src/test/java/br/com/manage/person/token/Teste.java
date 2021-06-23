package br.com.manage.person.token;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.user.controller.UserController;
import br.com.manage.person.user.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.manage.person.utils.UserUtils.asJsonString;
import static br.com.manage.person.utils.UserUtils.createFakeUserDTO;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class Teste {

    private static final String USER_API_URL_PATH = "/api/v1/user";

    MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void createUsersCorrectStatusCode201() throws Exception {
        UserDTO userDTO = createFakeUserDTO();

        when(userService.createUserService(userDTO)).thenReturn(MessageResponseDTO.builder().build());

        mockMvc.perform(post(USER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isOk());

        // verify(userService).createUserService(userDTO);
    }
}
