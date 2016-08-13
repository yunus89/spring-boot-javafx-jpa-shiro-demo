package demo.util;

import demo.model.Permission;
import demo.model.Role;
import demo.model.User;
import demo.services.UserManagerService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomSecurityRealm extends AuthorizingRealm {

    @Autowired
    private UserManagerService userManager;

    @Autowired
    private DefaultPasswordService passwordService;

    public CustomSecurityRealm() {
        this(new AllowAllCredentialsMatcher());
    }

    public CustomSecurityRealm(final CredentialsMatcher matcher) {
        super(matcher);
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Set roles = new HashSet<>();
        Set permissions = new HashSet<>();

        Collection<User> principalsList = principals.byType(User.class);
        for (User user : principalsList) {
            roles.addAll(user.getRolesName());
            for (Role role : user.getRoleList()) {
                for (Iterator iterator = role.getPermissionList().iterator(); iterator.hasNext(); ) {
                    Permission permission = (Permission)iterator.next();
                    permissions.add(new WildcardPermission(permission.getPermissionname()));
                }
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setRoles(roles);
        info.setObjectPermissions(permissions);

        return info;
    }

     @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upat = (UsernamePasswordToken) token;
        User user = userManager.findByUsername(upat.getUsername());
        if(user != null && passwordService.passwordsMatch(upat.getPassword(), user.getPassword())) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        else {
            throw new AuthenticationException("Invalid username/password combination!");
        }
    }
}