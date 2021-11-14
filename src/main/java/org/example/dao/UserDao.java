package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao  {
    User findByUsername(String username);
    List<User> index();
    void save(User user);
    User read(Long  id);
    void update(Long id, User user);
    void delete(Long id);
}
