package web.security.service;

import web.security.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void saveRole(Role role);

    List<Role> getAllRoles();

    Role findRoleByRoleName(String RoleName);

    Set<Role> getSetOfRoles(String[] roleName);

}
