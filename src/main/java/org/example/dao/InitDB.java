package org.example.dao;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleServce;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDB {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleServce roleServce;

    @PostConstruct
    private void postConstruct() {
        Role role;
        if( roleServce.findByRolename("ROLE_ADMIN") == null) {
            role = new Role("ROLE_ADMIN");
            roleServce.save(role);
        }
        if (roleServce.findByRolename("ROLE_USER") == null) {
            role = new Role("ROLE_USER");
            roleServce.save(role);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleServce.findByRolename("ROLE_ADMIN"));
        roles.add(roleServce.findByRolename("ROLE_USER"));

        if(userService.findByUsername("admin") == null) {
            User admin = new User("admin", "$2y$10$YLOfQ4E8fl9DxDmErmeeWe7YFliE3vU1nBYc3H6RalBA0q.6g9IKS", "admin@mail.ru", roles);
            userService.save(admin);
        }

        roles = new HashSet<>();
        roles.add(roleServce.findByRolename("ROLE_USER"));
        if( userService.findByUsername("user") == null) {
            User normalUser = new User("user", "$2y$10$ycyZJfq1ZnpRPCU0SrsrReJdUf5pRcjy7dIB1YymMSFufCOW1t4b6", "user@mail.com", roles);
            userService.save(normalUser);
        }

    }

}
