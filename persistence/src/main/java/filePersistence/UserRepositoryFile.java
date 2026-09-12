package filePersistence;

import entity.User;
import repository.IUserRepository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public class UserRepositoryFile implements IUserRepository {

    private FileReader<User> userFileReader;
    
    FileWriter userWriter = new FileWriter(Path.of("Users.csv"));

    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);

        String csvFormat = convertUserTOCsvFormat(user);

        try {
            userWriter.writeLineToFile(csvFormat);
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to save user to File", e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userFileReader.deserializeFile();
    }

    @Override
    public User findById(String id) {
        return null;
    }

    private String convertUserTOCsvFormat(User user) {
        return user.getId() + ", " + user.getName();
    }

}