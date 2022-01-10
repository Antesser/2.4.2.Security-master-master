package web.security.dao;

import org.springframework.stereotype.Repository;
import web.security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveNewUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public List<User> getUsersList() {
        return entityManager.createQuery("select distinct u from User u join fetch u.roles ", User.class).getResultList();
    }


    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public void updateUser(User toBeUpdated) {
        toBeUpdated.setId(toBeUpdated.getId());
        toBeUpdated.setUsername(toBeUpdated.getMyUsername());
        toBeUpdated.setPassword(toBeUpdated.getPassword());
        toBeUpdated.setName(toBeUpdated.getName());
        toBeUpdated.setLastName(toBeUpdated.getLastName());
        toBeUpdated.setAge(toBeUpdated.getAge());
        toBeUpdated.setRoles(toBeUpdated.getRoles());

        entityManager.merge(toBeUpdated);
    }


    @Override
    public void deleteUser(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User findByUserName(String username) {
        TypedQuery<User> q = entityManager.createQuery(
                "select  distinct u from User u  join fetch u.roles where  u.username =:ParamUsername", User.class);
        q.setParameter("ParamUsername", username);
        return q.getResultList().stream().findAny().orElse(null);
    }


}
