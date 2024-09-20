package oop.kolodina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * class for handling the structure of deck.
 */
public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    /**
     * filling the deck with cards.
     */
    public void fillDeck() {
        for (Card.Mark mark : Card.Mark.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                addToDeck(new Card(mark, rank));
            }
        }
    }

    /**
     * adding one card to the deck.
     * @param card to add
     */
    public void addToDeck(Card card) {
        cards.add(card);
    }

    /**
     * shuffling cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    /**
     * drawing one card from the deck
     * @return card we draw
     */
    public Card drawCard() {
        return cards.remove(0);
    }
}


