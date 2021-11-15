package org.example.dao;


import org.example.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        User user;
        try {
            user = entityManager.createQuery("select a from User a where a.username = ?1", User.class)
                    .setParameter(1, username).getSingleResult();
            System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }

        return user;
    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("select a from User a",User.class).getResultList();
    }

    @Override
    public void save(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            System.out.println("Ошибка добавления пользователя!");
            e.printStackTrace();
        }

    }

    @Override
    public User read(Long  id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(Long  id, User user) {
        try {
            entityManager.merge(user);
        } catch (HibernateException e) {
            System.out.println("Ошибка применения изменений! ");
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long  id) {
        entityManager.remove(entityManager.find(User.class,id));
    }

}
