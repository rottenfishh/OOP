package ru.nsu.kolodina.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.Nullable;

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
     *
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
     * drawing one card from the deck.
     * if card is empty in one of its fields null is returned
     *
     * @return card we draw
     */
    @Nullable
    public Card drawCard() {
        Card drawnCard = cards.remove(0);
        if (drawnCard == null || drawnCard.mark == null || drawnCard.rank == null) {
            System.out.println("Didn't draw card");
            return null;
        } else {
            return drawnCard;
        }
    }
}


