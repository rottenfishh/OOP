package oop.kolodina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Deck {
    private final List<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public void fillDeck(){
        for (Card.Mark mark: Card.Mark.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                addToDeck(new Card(mark, rank));
            }
        }
    }

    public void addToDeck(Card card){
        cards.add(card);
    }
    public void shuffle()
    {
        Collections.shuffle(cards, new Random());
    }

    public Card drawCard(){
        return cards.remove(0);
    }
}


