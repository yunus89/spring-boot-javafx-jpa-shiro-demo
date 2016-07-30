package demo.repositories;

import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserManagerRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT u.username FROM users u WHERE u.username = ?1", nativeQuery = true)
    User findByUsername(String username);
}
