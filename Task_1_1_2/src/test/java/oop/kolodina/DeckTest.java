package oop.kolodina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

  Deck deck;
  Card card1;

  @BeforeEach
  void init() {
    deck = new Deck();
    card1 = new Card(Card.Mark.DIAMOND, Card.Rank.QUEEN);
  }

  @Test
  public void fillDeckTest() {
    deck.fillDeck();
    assertNotNull(deck.drawCard());
  }

  @Test
  public void addingToDeckAndDrawingFromDeckTest() {
    deck.addToDeck(card1);
    deck.shuffle();
    Card card = deck.drawCard();
    assertEquals(card1, card);
  }
}