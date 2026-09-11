package memoryPersistence;

import entity.User;
import repository.IUserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        return List.of(allUsers.values().toArray(new User[0]));
    }

    @Override
    public User findById(String id) {
        return allUsers.get(id);

    }
}