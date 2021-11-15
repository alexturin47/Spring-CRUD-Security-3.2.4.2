package org.example.service;

import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    List<User> index();
    void save(User user);
    User read(Long id);
    void update(Long id, User user);
    void delete(Long id);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
