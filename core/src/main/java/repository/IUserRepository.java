package repository;

import entity.User;

import java.util.List;

public interface IUserRepository {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);
}