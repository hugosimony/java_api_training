package fr.lernejo.navy_battle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LauncherTest {

    private static int currentPort = 7890;

    @AfterEach
    public void nextPort() {
        currentPort++;
    }

    @Test
    public void noArgs() {
        // Should return if no args given
        String[] args = {};
        // It is just to pass more test, satisfying you know...
        Assertions.assertDoesNotThrow( () -> Launcher.main(args));
    }

    @Test
    public void goodPort() {
        // Check good port
        String[] args = { currentPort + "" };
        Assertions.assertDoesNotThrow( () -> Launcher.main(args));
    }

    @Test
    public void goodPortUrl() {
        // Check the first good port
        String[] args1 = { currentPort + "" };
        // Get to another port
        currentPort++;
        // Check the second good port and the correct url
        String[] args2 = { currentPort + "", "http://localhost:" + (currentPort-1) };
        Assertions.assertDoesNotThrow( () -> Launcher.main(args1));
        Assertions.assertDoesNotThrow( () -> Launcher.main(args2));
    }

    @Test
    public void badPortLong() {
        // Check too long port
        String[] args = { "29876543456789876543" };
        Assertions.assertThrows(Exception.class, () -> Launcher.main(args));
    }

    @Test
    public void badPortText() {
        // Check invalid text port
        String[] args = { "lorem ipsum" };
        Assertions.assertThrows(Exception.class, () -> Launcher.main(args));
    }
}
