package ru.nsu.kolodina.blackjack;

/**
 * class for handling the logic for the Player.
 */
public class Player {
    private final Hand playerHand;

    public Player() {
        this.playerHand = new Hand();
    }

    /**
     * returns player's hand.
     *
     * @return value - Hand with cards player has
     */
    public Hand getHand() {
        return this.playerHand;
    }

    /**
     * shows player's current deck.
     *
     * @param player - player
     */
    public void showDeck(Player player) {
        System.out.print("    Ваши карты: ");
        player.getHand().showHand();
        System.out.println("=>" + player.getHand().findValue());
    }

    /**
     * checks if player has Blackjack.
     *
     * @return true or false value
     */
    public boolean hasBlackJack() {
        return this.playerHand.findValue() == 21;
    }

}
