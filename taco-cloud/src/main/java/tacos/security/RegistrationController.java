package tacos.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tacos.MyUser;
import tacos.data.MyUserRepository;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register/design")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepo.save(user);
    }



}
