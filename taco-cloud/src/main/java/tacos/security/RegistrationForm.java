package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import org.springframework.stereotype.Component;
import tacos.User;

public class RegistrationForm {

    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, password);
    }

}
