package org.example.service;

import org.example.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public interface RoleServce {

    Role findByRolename(String rolename);

    HashSet<Role> index();

    void save(Role role);

    Role read(int id);

    void update(int id, Role role);

    void delete(int id);
}
