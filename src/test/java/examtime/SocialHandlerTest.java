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
        String actual = socialHandler.checkHandle("BandsMakeDance");
        //then
        assertEquals("@bandsmake", actual);
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
    @DisplayName("To check if handle contains spaces")
    void checkHandleWithSpaces() {
        assertThrows(IllegalArgumentException.class, ()
                -> {
            socialHandler.checkHandle("Rene Borja");
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

    @Test
    @DisplayName("To check if handle was removed")
    void removeHandleTest() throws Exception {
        //when
        socialHandler.addHandle("MikeJones");
        socialHandler.removeHandle("MikeJones");
        //then
        assertEquals(0, socialHandler.getUniqueHandles().size());
    }

    @Test
    @DisplayName("To check if message prints if there is no handle")
    void removeHandleTestNoSuchHandle() {
        //when
        socialHandler.addHandle("MikeJones");
        //then
        assertThrows(IllegalArgumentException.class, ()
                -> {
            socialHandler.removeHandle("jonesmike");
        });
    }

    @Test
    @DisplayName("To check if handle is null")
    void removeHandleTestNull() {
        //then
        assertThrows(NullPointerException.class, ()
                -> {
            socialHandler.removeHandle(null);
        });
    }

    @Test
    void updateHandleTest() {
        //when
        socialHandler.addHandle("reneborja");
        socialHandler.updateHandle("reneborja", "mikejones");
        assertFalse(socialHandler.getUniqueHandles().contains("@reneborja"));
        assertTrue(socialHandler.getUniqueHandles().contains("@mikejones"));
    }

    @Test
    void updateHandleNonExistingHandle() {
        socialHandler.addHandle("myhandle");
        socialHandler.updateHandle("nonexistinghandle", "newhandle");
        assertFalse(socialHandler.getUniqueHandles().contains("@newhandle"));
        assertTrue(socialHandler.getUniqueHandles().contains("@myhandle"));
    }

    @Test
    void updateHandleNull() {
        socialHandler.addHandle("existinghandle");
        assertThrows(NullPointerException.class, ()
                -> {
            socialHandler.updateHandle("existinghandle", null);
        });
    }

    @Test
    void updateHandleEmpty() {
        socialHandler.addHandle("myhandle");
        assertThrows(IllegalArgumentException.class, ()
                -> {
            socialHandler.updateHandle("myhandle", "");
        });
    }

    @Test
    void updateHandleExisting() {
        socialHandler.addHandle("reneborja");
        socialHandler.addHandle("mikejones");
        socialHandler.updateHandle("reneborja", "mikejones");
        assertTrue(socialHandler.getUniqueHandles().contains("@reneborja"));
        assertTrue(socialHandler.getUniqueHandles().contains("@mikejones"));
    }
}