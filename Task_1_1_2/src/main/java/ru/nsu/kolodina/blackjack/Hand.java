package ru.nsu.kolodina.blackjack;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * class handling hand structure(cards that player or dealer currently have).
 */
public class Hand {
    private final List<Card> cardsTaken;

    public Hand() {
        cardsTaken = new ArrayList<Card>();
    }

    /**
     * checks if player won.
     *
     * @param playerHand - player's hand
     * @param dealerHand - dealer's hand
     * @return -1 if dealer won, 1 if player won, 0 if its a draw
     */
    public static int didPlayerWin(Hand playerHand, Hand dealerHand) {
        int playerScore = playerHand.findValue();
        int dealerScore = dealerHand.findValue();

        if ((playerScore < dealerScore || playerScore > 21) && (dealerScore <= 21)) {
            return -1;
        } else if (playerScore > dealerScore || dealerScore > 21) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * taking one card from the deck.
     *
     * @param deck - deck to take card from
     */
    public void takeFromDeck(Deck deck) {
        this.addCard(deck.drawCard());
    }

    /**
     * adding card to the hand.
     *
     * @param card - card to add
     */
    public void addCard(Card card) {
        cardsTaken.add(card);
    }

    /**
     * showing card from the hand.
     *
     * @param idx - index of the card to show
     * @return - card to be shown
     */
    public Card showCard(int idx) {
        return cardsTaken.get(idx);
    }

    /**
     * showing last card taken to the hand.
     *
     * @return last card
     */
    @Nullable
    public Card showLastCard() {
        if (cardsTaken.size() == 0) {
            System.out.println("No cards to take");
            return null;
        } else {
            return cardsTaken.get(cardsTaken.size() - 1);
        }
    }

    /**
     * shows all cards on the hand at the moment.
     */
    public void showHand() {
        if (cardsTaken.size() == 0) {
            System.out.println("No cards to show");
        } else {
            System.out.print("[");
            for (int i = 0; i < cardsTaken.size() - 1; i++) {
                System.out.print(cardsTaken.get(i).toString() + ", ");
            }
            System.out.print(cardsTaken.get(cardsTaken.size() - 1));
            System.out.print("] ");
        }
    }

    /**
     * finds value of cards currently on hand.
     *
     * @return - the value of cards
     */
    public int findValue() {
        int value = 0;
        int acesCount = 0;
        for (Card card : cardsTaken) {
            value += card.getValue();
            if (card.getValue() == 11) {
                acesCount++;
            }
        }

        if (value > 21 && acesCount > 0) {
            while (acesCount > 0 && value > 21) {
                acesCount--;
                value -= 10;
            }
        }
        return value;
    }
}
