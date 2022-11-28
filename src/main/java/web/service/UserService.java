package web.service;

import web.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    List<User> getUsers();

    void addUser(@Valid User user);

    User getUserById(long id);

    void removeUser(long id);

    void updateUser(@Valid User user);

}
