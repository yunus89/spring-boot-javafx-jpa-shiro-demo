package demo.services;

import demo.model.Role;
import demo.model.User;
import java.util.List;

public interface UserManagerService {
    List<Role> findLoginUserRoles(User user);
    
    User findByUsername(String username);
    
    User addUser(User user);
}
