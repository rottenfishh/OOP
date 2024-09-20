package oop.kolodina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class DealerTest {
    Dealer dealer;
    Hand dealerHand;
    Card card1;
    Card card2;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        dealerHand = dealer.getHand();
        card1 = new Card(Card.Mark.DIAMOND, Card.Rank.FOUR);
        card2 = new Card(Card.Mark.DIAMOND, Card.Rank.ACE);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void showCardTest() {
        dealerHand.addCard(card1);
        Card card2 = dealerHand.showCard(0);
        assertEquals(card1, card2);
    }

    @Test
    public void showDeckTest() {
        dealerHand.addCard(card1);
        dealerHand.addCard(card2);
        dealer.showDeck(dealer, 1);
        String output = outputStream.toString().trim();
        String exceptedOutput = "Карты дилера: [Четыре бубны (4), Туз бубны (11)] =>15";
        assertEquals(exceptedOutput, output);
        outputStream.reset();
    }

    @Test
    public void hasBlackJackTest() {
        assertFalse(dealer.hasBlackJack());
    }
}
