package oop.kolodina;

public class Player {
    private final Hand playerHand;

    public Player() {
        this.playerHand = new Hand();
    }

    public Hand getHand() {
        return this.playerHand;
    }

    public void showDeck(Player player) {
        System.out.print("    Ваши карты: ");
        player.getHand().showHand();
        System.out.println("=>" + player.getHand().findValue());
    }

    public boolean hasBlackJack() {
        return this.playerHand.findValue() == 21;
    }

}
