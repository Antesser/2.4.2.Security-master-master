package web.security.service;

import web.security.model.Role;

import java.util.List;

public interface RoleService {
    void  saveRole(Role role);
    List<Role>  getAllRoles();
    Role findRoleByRoleName(String RoleName);

}
