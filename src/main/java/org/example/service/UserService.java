package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByUsername(String username);
    List<User> index();
    void save(User user);
    User read(int id);
    void update(int id, User user);
    void delete(int id);
}
