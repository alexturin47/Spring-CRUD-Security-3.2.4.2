package org.example.dao;

import org.example.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class RoleRepo implements RoleDao{

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public Role findByRolename(String rolename) {
        return entityManager.createQuery("select a from Role a where a.role = ?1", Role.class)
                            .setParameter(1, rolename).getSingleResult();
    }

    @Override
    public HashSet<Role> index() {
        return new HashSet<>(entityManager.createQuery("select a from Role a", Role.class).getResultList());
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role read(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void update(int id, Role role) {
        entityManager.merge(role);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }
}
