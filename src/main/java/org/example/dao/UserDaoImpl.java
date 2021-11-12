package org.example.dao;


import org.example.model.Role;
import org.example.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        User user = entityManager.createQuery("select a from User a where a.username = ?1", User.class)
                .setParameter(1, username).getSingleResult();
        System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getEmail());
        return user;
    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("select a from User a",User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User read(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class,id));
    }

}
