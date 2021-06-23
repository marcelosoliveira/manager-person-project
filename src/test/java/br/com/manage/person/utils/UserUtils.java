package br.com.manage.person.utils;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.exception.UserNameExistsException;
import br.com.manage.person.user.model.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.UUID;

public class UserUtils {

    private static final String USER_NAME = "jow";
    private static final String PASSWORD = "12345678";
    private static final UUID USER_ID = UUID.fromString("2dde6a22-0db6-424b-94eb-7d4105352163");

    public static UserDTO createFakeUserDTO() {
        return UserDTO.builder()
                .username(USER_NAME)
                .password(PASSWORD)
                .build();
    }

    public static User createFakeUser() {
        return User.builder()
                .id(USER_ID)
                .username(USER_NAME)
                .password(PASSWORD)
                .build();
    }

    public static void userNameExist(String userName, String userNameDTO) throws UserNameExistsException {
        if (userName == userNameDTO) throw new UserNameExistsException("User already exists!");
    }

    public static String asJsonString(Object userDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
