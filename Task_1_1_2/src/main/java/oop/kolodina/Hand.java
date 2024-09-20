package oop.kolodina;
import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private ArrayList<Card> cardsTaken;

    public Hand(){
        cardsTaken = new ArrayList<Card>();
    }

    public void takeFromDeck(Deck deck){
        this.addCard(deck.drawCard());
    }

    public void addCard(Card card){
        cardsTaken.add(card);
    }
    public Card showCard(int idx){
        return cardsTaken.get(idx);
    }

    public Card showLastCard(){
        return cardsTaken.get(cardsTaken.size()-1);
    }
    public void showHand(){
        System.out.print("[");
        for (int i = 0; i<cardsTaken.size() - 1 ; i++){
            System.out.print(cardsTaken.get(i).toString() + ", ");
        }
        System.out.print(cardsTaken.get(cardsTaken.size()-1));
        System.out.print("] ");
    }
    public int findValue(){
        int value = 0;
        int acesCount = 0;
        for (Card card: cardsTaken){
            value += card.getValue();
            if (card.getValue() == 11){
                acesCount++;
            }
        }

        if (value > 21 && acesCount >0){
            while (acesCount > 0 && value > 21){
                acesCount --;
                value -= 10;
            }
        }
        return value;
    }
    public static int didPlayerWin(Hand playerHand, Hand dealerHand){
        int playerScore = playerHand.findValue();
        int dealerScore = dealerHand.findValue();

        if ((playerScore < dealerScore || playerScore > 21) && (dealerScore<=21)){
            return -1;
        }
        else if (playerScore > dealerScore || dealerScore > 21){
            return 1;
        }
        else {
            return 0;
        }
    }
}
