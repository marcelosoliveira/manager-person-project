package br.com.manage.person.mapper;

import br.com.manage.person.dto.request.PersonDTO;
import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.person.model.Person;
import br.com.manage.person.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toPersonModel(PersonDTO personDTO);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    PersonDTO toPersonDTO(Person person);

    User toUserModel(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
