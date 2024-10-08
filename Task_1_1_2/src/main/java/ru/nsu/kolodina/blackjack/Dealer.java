package ru.nsu.kolodina.blackjack;

/**
 * class to handle logic of dealer.
 */
public class Dealer {
    private final Hand dealerHand;

    public Dealer() {
        this.dealerHand = new Hand();
    }

    /**
     * get dealer's hand.
     *
     * @return hand consisting of cards dealer has
     */
    public Hand getHand() {
        return this.dealerHand;
    }

    /**
     * show one card from dealer's hand.
     *
     * @param idx index of the card to draw
     * @return card we draw
     */
    public Card showCard(int idx) {
        return this.dealerHand.showCard(idx);
    }

    /**
     * show deck dealer currently has.
     *
     * @param dealer - dealer
     * @param isOpened -true or false value that shows if dealer opened closed card
     */
    public void showDeck(Dealer dealer, boolean isOpened) {
        if (!isOpened) {
            Card firstCard = dealer.showCard(0);
            System.out.print("    Карты дилера: ");
            System.out.print("[");
            System.out.print(firstCard.toString() + ", ");
            System.out.println("<закрытая карта>]");
        } else {
            System.out.print("    Карты дилера: ");
            dealer.getHand().showHand();
            System.out.println("=>" + dealer.getHand().findValue());
        }
    }

    /**
     * checks if dealer hasBlackJack.
     *
     * @return true or false value
     */
    public boolean hasBlackJack() {
        return this.dealerHand.findValue() == 21;
    }
}
