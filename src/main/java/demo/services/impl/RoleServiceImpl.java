package demo.services.impl;

import demo.model.Role;
import demo.repositories.RoleRepository;
import demo.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
    
}
