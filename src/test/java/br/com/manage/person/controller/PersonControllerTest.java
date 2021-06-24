package br.com.manage.person.controller;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.exception.PersonNotFoundException;
import br.com.manage.person.person.controller.PersonController;
import br.com.manage.person.person.service.impl.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;

import static br.com.manage.person.utils.PersonUtils.createFakeDTO;
import static br.com.manage.person.utils.UserUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    private static final String PEOPLE_API_URL_PATH = "/api/v1/people";

    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personController = new PersonController(personService);
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTIsCalledThenAPersonShouldBeCreated() throws Exception {
        PersonDTO expectedPersonDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = createMessageResponse("Successfully registered person", 1L);

        when(personService.create(expectedPersonDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(post(PEOPLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedPersonDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

    @Test
    void testWhenGETWithValidIsCalledThenAPersonShouldBeReturned() throws Exception {
        var expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        expectedPersonDTO.setId(expectedValidId);

        when(personService.findPeople(expectedValidId)).thenReturn(expectedPersonDTO);

        mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Ana Marcia")))
                .andExpect(jsonPath("$.lastName", is("Santos")));
    }

    @Test
    void testWhenGETWithInvalidIsCalledThenAnErrorMessagenShouldBeReturned() throws Exception {
        var expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        expectedPersonDTO.setId(expectedValidId);

        when(personService.findPeople(expectedValidId)).thenThrow(PersonNotFoundException.class);

        mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testWhenGETIsCalledThenPeopleListShouldBeReturned() throws Exception {
        var expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        expectedPersonDTO.setId(expectedValidId);
        List<PersonDTO> expectedPeopleDTOList = Collections.singletonList(expectedPersonDTO);

        when(personService.findPeopleAll()).thenReturn(expectedPeopleDTOList);

        mockMvc.perform(get(PEOPLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Ana Marcia")))
                .andExpect(jsonPath("$[0].lastName", is("Santos")));
    }

    @Test
    void testWhenPUTIsCalledThenAPersonShouldBeUpdated() throws Exception {
        var expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = createMessageResponse("Person successfully updated with ID", 1L);

        when(personService.updatePeople(expectedValidId, expectedPersonDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(put(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedPersonDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

    @Test
    void testWhenDELETEIsCalledThenAPersonShouldBeDeleted() throws Exception {
        var expectedValidId = 1L;

        mockMvc.perform(delete(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}