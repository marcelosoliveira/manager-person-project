package br.com.manage.person.user.service.impl;

import br.com.manage.person.dto.request.UserDTO;
import br.com.manage.person.dto.response.MessageResponseDTO;
import br.com.manage.person.exception.PersonNotFoundException;
import br.com.manage.person.exception.UserNameExistsException;
import br.com.manage.person.user.model.User;
import br.com.manage.person.user.repository.UserRepository;
import br.com.manage.person.user.service.interfaces.UserInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService, UserInterface {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public MessageResponseDTO createUserService(UserDTO userDTO) {
        verifyUsername(userDTO.getUsername());
        String passwordEncode = encodePassword(userDTO.getPassword());
        userDTO.setPassword(passwordEncode);
        User user = toUser(userDTO);
        this.userRepository.save(user);
        return createMessageResponse("User created successfully");
    }

    @Override
    public User toUser(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO toUserDTO(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }

    private MessageResponseDTO createMessageResponse(String message) {
        return MessageResponseDTO
                .builder()
                .message(message)
                .build();
    }

    @Override
    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(this.userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("No user or invalid password"));
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList(
                "ROLE_USER");
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorityListUser);
    }

    @Override
    public void verifyUsername(String username) throws UserNameExistsException {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByUsername(username));
        if (user.isPresent()) throw new UserNameExistsException("User already exists!");
    }

    public List<UserDTO> findUsersAll() {
        List<User> userList = this.userRepository.findAll();
        return userList.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

}
