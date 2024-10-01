package ru.nsu.kolodina.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * tests for Card class.
 */
public class CardTest {
    public Card testCard1;
    public Card testCard2;
    public Card testCard3;

    /**
     * creating cards for tests.
     */
    @BeforeEach
    void createCards() {
        testCard1 = new Card(Card.Mark.CLUB, Card.Rank.EIGHT);
        testCard2 = new Card(Card.Mark.DIAMOND, Card.Rank.QUEEN);
        testCard3 = new Card(Card.Mark.HEART, Card.Rank.TWO);
    }

    /**
     * testing if value is taken correctly by getValue() method.
     */
    @Test
    public void testGetValue() {
        assertEquals(8, testCard1.getValue());
        assertEquals(10, testCard2.getValue());
        assertEquals(2, testCard3.getValue());
    }

    /**
     * testing if cards get converted to Strings correctly.
     */
    @Test
    public void testToString() {
        String string1 = testCard1.rank.getRuName() + " " + testCard1.mark.getRuName()
                + " (" + testCard1.getValue() + ")";
        String string2 = testCard2.rank.getRuName() + " " + testCard2.mark.getRuName()
                + " (" + testCard2.getValue() + ")";
        String string3 = testCard3.rank.getRuName() + " " + testCard3.mark.getRuName()
                + " (" + testCard3.getValue() + ")";
        assertEquals(string1, testCard1.toString());
        assertEquals(string2, testCard2.toString());
        assertEquals(string3, testCard3.toString());
    }

    /**
     * testing if isAce return correct values.
     */
    @Test
    public void testIsAce() {
        Card ace1 = new Card(Card.Mark.DIAMOND, Card.Rank.ACE);
        assertTrue(ace1.isAce());

        Card ace2 = new Card(Card.Mark.HEART, Card.Rank.TEN);
        assertFalse(ace2.isAce());
    }
}