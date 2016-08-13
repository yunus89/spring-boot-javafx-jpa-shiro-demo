package demo.repositories;

import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagerRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
