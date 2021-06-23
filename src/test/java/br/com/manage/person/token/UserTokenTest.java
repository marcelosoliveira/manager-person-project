package br.com.manage.person.token;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.user.controller.UserController;
import br.com.manage.person.user.model.User;
import br.com.manage.person.user.repository.UserRepository;
import br.com.manage.person.user.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.manage.person.utils.UserUtils.createFakeUser;
import static br.com.manage.person.utils.UserUtils.createFakeUserDTO;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserTokenTest {

//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    private int PORT;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

//    private HttpEntity<Void> userHeader;
//    private HttpEntity<Void> wrongHeader;

//    @TestConfiguration
//    static class Config{
//        @Bean
//        public RestTemplateBuilder restTemplateBuilder() {
//            return new RestTemplateBuilder()
//                    .basicAuthentication("admin", "admin");
//        }
//    }


//    @Before
//    public void configUserHeader() {
//        String str = "grant_type=password" + "&username=" + createFakeUser().getUsername()
//                + "&password=" + createFakeUser().getPassword();
//        HttpHeaders headers = restTemplate.postForEntity(
//                "/oauth/token", str, String.class).getHeaders();
//        System.out.println(headers.getAccessControlAllowCredentials());
//        this.userHeader = new HttpEntity<>(headers);
//    }

//    @Before
//    public void configUserHeader() {
//        String str = "{  \"grant_type\": \"password\", \"username\": \"jow\", \"password\": \"12345678\" }";
//        HttpHeaders headers = restTemplate.postForEntity(
//                "/oauth/token", str, String.class).getHeaders();
//        this.userHeader = new HttpEntity<>(headers);
//    }
//
//    @Before
//    public void configWrongHeader() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "039c9cfa-6c91-434d-90d8-c5d9a0a03a59");
//        this.wrongHeader = new HttpEntity<>(headers);
//    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

//    @Test
//    public void testCreateTokenCorrect() throws Exception {
//        System.out.println(wrongHeader);
//        UserDTO userDTO = createFakeUserDTO();
//        userService.createUserService(userDTO);
//        mockMvc.perform(post("/oauth/token")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("\"{\"grant_type\": \"password\", \"username\": \"jow\", \"password\": \"12345678\" }\""))
//                .andExpect(status().isCreated());
//    }

    @Test
    public void createUsersCorrectStatusCode201() throws Exception {
          UserDTO userDTO = createFakeUserDTO();

          //when(userService.createUserService(userDTO)).thenReturn("\" {\"message\": \"User created successfully\" }\"");

          mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTO.toString()))
                .andExpect(status().isCreated());

        //verify(userService).createUserService(userDTO);
    }

//    @Test
//    public void listAllUsersCorrectStatusCode200() {
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                "/api/v1/user", HttpMethod.POST, userHeader, String.class);
//        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
//    }
}
