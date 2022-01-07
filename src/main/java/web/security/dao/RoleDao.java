package web.security.dao;

import web.security.model.Role;

import java.util.List;

public interface RoleDao {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Role findRoleByRoleName(String RoleName);



}
