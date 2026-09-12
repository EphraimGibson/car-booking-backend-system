package service;

import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.IUserRepository;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUserRepository userRepositoryMock;
    @InjectMocks
    private UserService userService;

    @Test
    void testShouldCreateUserSuccessfully() {
        //Given
        User testUser = new User("John");
        when(userRepositoryMock.saveUser(any())).thenReturn(testUser);

        //When
        User result = userService.createUser(testUser);

        //Then
        Assertions.assertEquals(testUser, result, " returned user does not match");
        verify(userRepositoryMock, times(1)).saveUser(any());
    }

    @Test
    void testGetAllUsersSuccessful() {
        //Given
        User testUser = new User("John");
        when(userRepositoryMock.getAllUsers()).thenReturn(List.of(testUser));

        //When
        List<User> result = userService.getAllUsers();

        //Then
        Assertions.assertNotNull(result);

        boolean userExists = false;
        for (User user : result) {
            if (Objects.equals(user.getName(), testUser.getName())) {
                userExists = true;
                break;
            }
        }
        Assertions.assertTrue(userExists, "User" + testUser.getName() + "does not exist in the returned users");
        Assertions.assertEquals(1, result.size(), "user results should have only one user");
        verify(userRepositoryMock, times(1)).getAllUsers();
    }

    @Test
    void testGetUserByIdSuccessful() {
        //Given
        String id = "testID";
        User testUser = new User(id, "John");


        when(userRepositoryMock.findById(id)).thenReturn(testUser);

        //When
        User result = userService.getUserById(id);

        //Then
        Assertions.assertEquals(id, result.getId(), String.format("user with id: %s is not found", id));
        verify(userRepositoryMock, times(1)).findById(any());
    }
}