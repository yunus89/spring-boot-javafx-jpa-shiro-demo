package demo.services.impl;

import demo.model.Permission;
import demo.repositories.PermissionRepository;
import demo.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PermissionServiceImpl implements PermissionService{
    private final PermissionRepository permissionRepository;
    
    @Override
    public Permission addPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
}
