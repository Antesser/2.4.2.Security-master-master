package web.security.dao;

import web.security.model.User;

import java.util.List;

public interface UserDao {


    void saveNewUser(User user);

    List<User> getUsersList();

    User findById(Long id);

    void updateUser(User toBeUpdated);

    void deleteUser(Long id);

    User findByUserName(String username);



}
