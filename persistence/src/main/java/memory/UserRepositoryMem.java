package memory;

import entity.User;
import repository.IUserRepository;

import java.util.*;

public class UserRepositoryMem implements IUserRepository {

    private final Map<String, User> allUsers = new HashMap<>();

    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        allUsers.put(id, user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(String id) {
        return allUsers.get(id);
        //TODO rules say not to return null, maybe throw exception


    }
}