package filePersistence;

import entity.User;
import repository.IUserRepository;

import java.util.List;

public class UserRepositoryFile implements IUserRepository {
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}