package ru.nsu.kolodina.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.blackjack.Hand.didPlayerWin;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for Hand class.
 */
public class HandTest {
    Deck deck;
    Hand hand;
    Card card1;
    Card card2;

    private ByteArrayOutputStream outputStream;

    /**
     * initializing necessary objects.
     */
    @BeforeEach
    void init() {
        deck = new Deck();
        hand = new Hand();
        card1 = new Card(Card.Mark.DIAMOND, Card.Rank.FOUR);
        card2 = new Card(Card.Mark.DIAMOND, Card.Rank.TEN);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * testing if card is taken from the deck correctly.
     */
    @Test
    public void testTakeFromDeck() {
        deck.addToDeck(card1);
        hand.takeFromDeck(deck);
        assertEquals(card1, hand.showCard(0));
    }

    /**
     * testing if card is shown correctly.
     */
    @Test
    public void testShowCard() {
        deck.addToDeck(card1);
        hand.takeFromDeck(deck);
        Card card = hand.showCard(0);
        assertEquals(card1, card);
    }

    @Test
    public void testAddCard() {
        Card card2 = new Card(Card.Mark.DIAMOND, Card.Rank.TEN);
        hand.addCard(card2);
        Card card = hand.showCard(0);
        assertEquals(card2, card);
    }

    @Test
    public void testShowLastCard() {
        hand.addCard(card2);
        Card lastCard = hand.showLastCard();
        Card card = hand.showCard(0);
        assertEquals(card, lastCard);
    }

    @Test
    public void testShowHand() {
        deck.addToDeck(card1);
        hand.takeFromDeck(deck);
        hand.showHand();
        String output = outputStream.toString().trim();
        String expectedOutput = "[" + card1.toString() + "]";
        assertEquals(expectedOutput, output);
        outputStream.reset();
    }

    @Test
    public void testFindValue() {
        hand.addCard(card1);
        int value = hand.findValue();
        assertEquals(4, value);
    }

    @Test
    public void testDidPlayerWin() {
        Card cardPlayer1 = new Card(Card.Mark.DIAMOND, Card.Rank.ACE);
        Card cardPlayer2 = new Card(Card.Mark.DIAMOND, Card.Rank.TWO);

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        playerHand.addCard(cardPlayer1);
        playerHand.addCard(cardPlayer2);

        Card cardDealer1 = new Card(Card.Mark.DIAMOND, Card.Rank.THREE);
        Card cardDealer2 = new Card(Card.Mark.DIAMOND, Card.Rank.TWO);

        dealerHand.addCard(cardDealer1);
        dealerHand.addCard(cardDealer2);

        int result = didPlayerWin(playerHand, dealerHand);

        assertEquals(result, 1);
    }
}
