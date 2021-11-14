package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByUsername(String username);
    List<User> index();
    void save(User user);
    User read(Long id);
    void update(Long id, User user);
    void delete(Long id);
}
