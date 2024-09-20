package oop.kolodina;

public class Dealer {
    private final Hand dealerHand;

    public Dealer() {
        this.dealerHand = new Hand();
    }

    public Hand getHand() {
        return this.dealerHand;
    }

    public Card showCard(int idx) {
        return this.dealerHand.showCard(idx);
    }

    public void showDeck(Dealer dealer, int isOpened) {
        if (isOpened == 0) {
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

    public boolean hasBlackJack() {
        return this.dealerHand.findValue() == 21;
    }
}
