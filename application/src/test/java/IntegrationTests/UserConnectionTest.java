package IntegrationTests;

import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;

class UserConnectionTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        TestApplicationContext instance = TestApplicationContext.getInstance();
        userService = instance.getUserservice();

    }

    @Test
    void testShouldSaveUserSuccessFully() {
        //Given
        User testUserJohn = new User("TestUserJohn");

        //When
        User result = userService.createUser(testUserJohn);

        Assertions.assertNotNull(result.getId(), "User ID should not be null");

        result = userService.getUserById(result.getId());
        Assertions.assertNotNull(result, "saved User not found ");
        //Then
        Assertions.assertEquals(testUserJohn.getName(), result.getName());
    }

}