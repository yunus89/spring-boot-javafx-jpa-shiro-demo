package demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="roles")
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String rolename;
    @OneToMany
    @JoinTable( name = "role_permission", 
        joinColumns =           @JoinColumn(name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns =    @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private List<Permission> permissionList = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roleList")
    private List<User> userList = new ArrayList<>();
    
    @Transient
    private Set<String> permissionsName;
    
    public Set<String> getPermissionsName() {
        permissionsName = new HashSet<String>();
        List<Permission> perlist = getPermissionList();
        for (Permission per : perlist) {
            permissionsName.add(per.getPermissionname());
        }
        return permissionsName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    
}
