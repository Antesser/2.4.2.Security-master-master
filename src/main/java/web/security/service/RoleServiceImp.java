package web.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.security.dao.RoleDao;
import web.security.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public Role findRoleByRoleName(String RoleName) {
        return roleDao.findRoleByRoleName(RoleName);
    }

    @Transactional
    @Override
    public Set<Role> getSetOfRoles(String[] roleName) {
        return roleDao.getSetOfRoles(roleName);
    }


}
