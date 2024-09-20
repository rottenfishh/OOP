package oop.kolodina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    Player player;
    Hand playerHand;
    Card card1;
    Card card2;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void init() {
        player = new Player();
        playerHand = player.getHand();
        card1 = new Card(Card.Mark.DIAMOND, Card.Rank.TEN);
        card2 = new Card(Card.Mark.SPADE, Card.Rank.ACE);
        playerHand.addCard(card1);
        playerHand.addCard(card2);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void showDeckTest() {
        player.showDeck(player);
        String output = outputStream.toString().trim();
        String exceptedOutput = "Ваши карты: [Десять бубны (10), Туз пики (11)] =>21";
        assertEquals(exceptedOutput, output);
        outputStream.reset();
    }

    @Test
    public void hasBlackJackTest() {
        assertTrue(player.hasBlackJack());
    }
}
