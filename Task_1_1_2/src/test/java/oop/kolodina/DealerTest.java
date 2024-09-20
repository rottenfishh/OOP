package oop.kolodina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for Dealer class.
 */
public class DealerTest {
    Dealer dealer;
    Hand dealerHand;
    Card card1;
    Card card2;
    private ByteArrayOutputStream outputStream;

    /**
     * initializing necessary objects.
     */
    @BeforeEach
    void init() {
        dealer = new Dealer();
        dealerHand = dealer.getHand();
        card1 = new Card(Card.Mark.DIAMOND, Card.Rank.FOUR);
        card2 = new Card(Card.Mark.DIAMOND, Card.Rank.ACE);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * testing if card is shown from dealer's hand correctly.
     */
    @Test
    public void showCardTest() {
        dealerHand.addCard(card1);
        Card card2 = dealerHand.showCard(0);
        assertEquals(card1, card2);
    }

    /**
     * testing if deck is shown correctly.
     */
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

    /**
     * testing if hasBlackJack works correctly.
     * Dealer doesnt have 21, so it should return false
     */
    @Test
    public void hasBlackJackTest() {
        dealerHand.addCard(card1);
        dealerHand.addCard(card2);
        assertFalse(dealer.hasBlackJack());
    }
}
