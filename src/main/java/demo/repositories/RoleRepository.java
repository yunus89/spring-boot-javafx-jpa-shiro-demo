/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.repositories;

import demo.model.Role;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Yunus
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
