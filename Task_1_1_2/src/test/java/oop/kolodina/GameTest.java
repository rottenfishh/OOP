package oop.kolodina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest {
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    void init(){
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    public void testGame(){
        String input = "1\n0\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Game game = new Game();
        game.startGame();
        String output = outputStream.toString();
        assertTrue(output.contains("Дилер раздал карты"));
        assertTrue(output.contains("Ваш ход"));
        assertTrue(output.contains("Ваши карты"));
        assertTrue(output.contains("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться . Введите 2, чтобы выйти из игры"));
        assertTrue(output.contains("Счет"));
    }

}
