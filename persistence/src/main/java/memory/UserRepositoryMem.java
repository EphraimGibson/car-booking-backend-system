package memory;

import entity.User;
import repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMem implements IUserRepository {

    private final List<User> allUsers = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        allUsers.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return allUsers;
    }

    @Override
    public User getUserById(String id) {

        for (User user : allUsers) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        //TODO rules say not to return null, maybe throw exception

        return null;
    }
}