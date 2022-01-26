package web.security.dao;

import org.springframework.stereotype.Repository;
import web.security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext//предназаначена для автоматического связывания менеджера сущностей с бином.
    private EntityManager manager;


    @Override
    public void saveRole(Role role) {
        if (findRoleByRoleName(role.getName()) != null) {
            manager.merge(role);
        }
        manager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return manager.createQuery(" select  r from Role r", Role.class).getResultList();
    }

    @Override
    public Role findRoleByRoleName(String RoleName) {
        TypedQuery<Role> query = manager.createQuery("select r from Role r  where  r.name=:paramName", Role.class);
        query.setParameter("paramName", RoleName);
        return query.getResultList().stream().findAny().orElse(null);
    }
    @Override
    public Set<Role> getSetOfRoles(String[] roleName) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleName) {
            roleSet.add(findRoleByRoleName(role));
        }
        return roleSet;
    }
}
