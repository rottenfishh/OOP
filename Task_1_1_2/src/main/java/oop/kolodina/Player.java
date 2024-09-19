package oop.kolodina;

public class Player {
    private Hand playerHand;

    public Player(){
        this.playerHand = new Hand();
    }

    public Hand getHand(){
        return this.playerHand;
    }

    public void showDeck(Player player){
        System.out.print("    Ваши карты: ");
        player.getHand().showHand();
        System.out.println("=>" + player.getHand().findValue());
    }

    public boolean hasBlackJack(){
        if (this.playerHand.findValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }

}
