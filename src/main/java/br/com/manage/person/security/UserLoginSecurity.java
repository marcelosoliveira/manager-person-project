package br.com.manage.person.security;

import br.com.manage.person.user.model.User;
import br.com.manage.person.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginSecurity {

    private UserRepository userRepository;

    public User getLoginUser() {
        String username;
        Object userLogin = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userLogin instanceof UserDetails) {
            username = ((UserDetails) userLogin).getUsername();
        } else {
            username = userLogin.toString();
        }

        return this.userRepository.findByUsername(username);
    }
}
