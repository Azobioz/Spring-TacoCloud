package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.web.MyUserDetailsService;


@EnableWebSecurity
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              return http
                      .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/home", "/register/**").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/design/**", "/orders/**").hasRole("USER");
                    registry.anyRequest().authenticated();
              })
                      .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                      .build();

    }



//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails normalUser = User.builder()
//                        .username("NormalUser")
//                        .password("$2a$12$92oMmW2pZUD9WGSk9EpBp.Z.9cD5dXK98j0UMJIM4XgcgQvsiWfSy") //12345
//                        .roles("USER")
//                        .build();
//
//        UserDetails adminUser = User.builder()
//                        .username("AdminUser")
//                        .password("$2a$12$b7zE4gzv0ugqjvMCquxuQeVGwJeZAGAWqvi8/Gpm.QWOlaD9wcsRO") // 54321
//                        .roles("ADMIN", "USER")
//                        .build();
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }


}
