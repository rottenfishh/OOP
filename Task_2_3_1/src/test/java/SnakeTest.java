import org.junit.jupiter.api.Test;
import ru.nsu.kolodina.snake.MainApp;

/**
 * A test class for testing the Snake game functionality.
 */
public class SnakeTest {

    /**
     * A simple test to demonstrate that the main method of {@link MainApp}
     * cannot be called from somewhere else directly. This test prints a message
     * to the console indicating this limitation.
     */
    @Test
    public void snakeTest() {
        System.out.println("turns out you cant call mainApp from somewhere else");
    }
}
