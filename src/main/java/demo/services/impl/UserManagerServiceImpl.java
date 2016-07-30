package demo.services.impl;

import demo.model.Role;
import demo.model.User;
import demo.repositories.UserManagerRepository;
import demo.services.UserManagerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserManagerServiceImpl implements UserManagerService{
    private final UserManagerRepository userManagerRepository;
    
    @Override
    public List<Role> findLoginUserRoles(User user) {
        return userManagerRepository.findOne(user.getId()).getRoleList();
    }

    @Override
    public User findByUsername(String username) {
        return userManagerRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        return userManagerRepository.save(user);
    }
    
}
