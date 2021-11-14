package org.example.service;

import org.example.dao.RoleDao;
import org.example.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class RoleServiceImpl implements RoleServce {

    @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public Role findByRolename(String rolename) {
        return roleDao.findByRolename(rolename);
    }

    @Override
    public HashSet<Role> index() {
        return roleDao.index();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role read(int id) {
        return roleDao.read(id);
    }

    @Override
    public void update(int id, Role role) {
        roleDao.update(id, role);
    }

    @Override
    public void delete(int id) {
        roleDao.delete(id);
    }

}
