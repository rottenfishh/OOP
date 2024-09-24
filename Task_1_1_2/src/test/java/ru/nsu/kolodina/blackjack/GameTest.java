package ru.nsu.kolodina.blackjack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.kolodina.blackjack.Game;

/**
 * tests for Game class.
 */
public class GameTest {
    private ByteArrayOutputStream outputStream;

    /**
     * creating streams to imitate input for the game flow.
     */
    @BeforeEach
    void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * checking if game outputs strings it needs.
     * to write for the player to interact with the game
     */
    @Test
    public void testGame() {
        for (int i = 0; i < 10; i++) {
            String input = "1\n0\n2\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Game game = new Game();
            game.startGame();
            String output = outputStream.toString();
            assertTrue(output.contains("Дилер раздал карты"));
            if (!(output.contains("блекджек") || (output.contains("Ничья!")))) {
                assertTrue(output.contains("Ваш ход"));
                assertTrue(output.contains("Введите “1”, чтобы взять карту, и “0”,"
                        + " чтобы остановиться. Введите 2, чтобы выйти из игры"));
                assertTrue(output.contains("Вы открыли карту"));
                assertTrue(output.contains("Вы проиграли раунд!")
                        || output.contains("блекджек")
                        || (output.contains("Ход дилера")
                        && output.contains("Дилер открывает карту")));
                assertTrue(output.contains("Вы проиграли раунд")
                        || output.contains("Вы выиграли раунд")
                        || output.contains("Ничья! Счет") || output.contains("блекджек"));
            } else {
                assertTrue(output.contains("Счет"));
            }
        }
    }

}
