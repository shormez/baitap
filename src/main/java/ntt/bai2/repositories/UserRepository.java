package ntt.bai2.repositories;

import ntt.bai2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);

    //   public User getUserByUsername(String username);

}
