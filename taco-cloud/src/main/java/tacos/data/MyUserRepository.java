package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.MyUser;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);

}
