package web.security.service;

import web.security.model.User;

import java.util.List;


public interface UserService {
    void saveNewUser(User user);

    List<User> getUsersList();

    User findById(long id);

    void updateUser(User user);

    void deleteUser(long id);

    User findByUserName(String name);


}
