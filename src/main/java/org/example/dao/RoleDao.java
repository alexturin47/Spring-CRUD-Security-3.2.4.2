package org.example.dao;

import org.example.model.Role;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface RoleDao {
    Role findByRolename(String rolename);
    HashSet<Role> index();
    void save(Role role);
    Role read(int id);
    void update(int id, Role role);
    void delete(int id);
}
