package examtime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocialHandlerTest {

    private SocialHandler socialHandler;

    @BeforeEach
    void setUp() {
        socialHandler = new SocialHandler();
    }

    @Test
    @DisplayName("To check if a social media handle was created successfully")
    void createHandleTest() {
        //when
        String actual = socialHandler.checkHandle("12345678910");
        //then
        assertEquals("@123456789", actual);
    }

    @Test
    @DisplayName("To check if handle was created with 9 characters or less")
    void checkHandleLessThan9CharactersTest() {
        //when
        String actual = socialHandler.checkHandle("123456");
        //then
        assertEquals("@123456", actual);
    }

    @Test
    @DisplayName("To check if social media input is null")
    void checkIfNullTest() {
        assertThrows(NullPointerException.class, ()
                -> {
            socialHandler.checkHandle(null);
        });
    }

    @Test
    @DisplayName("To check if social media handle is empty or blank")
    void checkEmptyOrBlankCharactersTest() {
        assertThrows(IllegalArgumentException.class, ()
                -> {
            socialHandler.checkHandle("");
        });
    }

    @Test
    @DisplayName("To check if handle was added despite multiple attempts")
    void addHandleTest() {
        //when
        socialHandler.addHandle("Mikejones");
        socialHandler.addHandle("MikeJones");
        socialHandler.addHandle("mikeJones");
        //then
        assertEquals(1, socialHandler.getUniqueHandles().size());

    }
}