package web.security.dao;

import org.springframework.stereotype.Repository;
import web.security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
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



}
