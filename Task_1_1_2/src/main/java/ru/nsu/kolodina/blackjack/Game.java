package ru.nsu.kolodina.blackjack;

import java.util.Scanner;

/**
 * class for handling the Blackjack game flow.
 */
public class Game {

    private Deck deck;

    private Dealer dealer;

    private Hand dealerHand;

    private Player player;

    private Hand playerHand;

    /**
     * method for initializing necessary objects and starting the game.
     */
    public void startGame() {
        deck = new Deck();
        deck.fillDeck();
        player = new Player();
        dealer = new Dealer();
        dealerHand = dealer.getHand();
        playerHand = player.getHand();
        newRound();
    }

    /**
     * method that handles game logic.
     */
    public void newRound() {
        Scanner scanner = new Scanner(System.in);
        deck.shuffle();

        playerHand.takeFromDeck(deck);
        playerHand.takeFromDeck(deck);

        dealerHand.takeFromDeck(deck);
        dealerHand.takeFromDeck(deck);

        Card cardOpened;

        System.out.println("Дилер раздал карты");
        player.showDeck(player);

        dealer.showDeck(dealer, 0);

        if (dealer.hasBlackJack()) {
            if (player.hasBlackJack()) {
                System.out.println("Ничья!");
                Main.draws++;
                return;
            } else {
                Main.losses++;
                System.out.println("Дилер получил блекджек. Вы проиграли раунд! Счет "
                        + Main.losses + ":" + Main.wins + " в пользу дилера");
            }
            return;
        }

        if (player.hasBlackJack()) {
            Main.wins++;
            System.out.println("Вы получили блекджек и выиграли раунд! Счет "
                    + Main.wins + ":" + Main.losses + " в вашу пользу!");
            return;
        }

        System.out.println("Ваш ход");
        System.out.println("--------");

        int choice = -1;

        while (choice != 0 && player.getHand().findValue() <= 21 && choice != 2) {
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться. "
                    + "Введите 2, чтобы выйти из игры");
            choice = scanner.nextInt();
            if (choice == 1) {
                playerHand.takeFromDeck(deck);
                cardOpened = player.getHand().showLastCard();
                System.out.println("Вы открыли карту " + cardOpened.toString());
                player.showDeck(player);
                dealer.showDeck(dealer, 0);
            }
            if (choice == 2) {
                Main.stopGame = true;
                return;
            }
            if (player.hasBlackJack()) {
                Main.wins++;
                System.out.println("Вы получили блекджек и выиграли раунд! Счет "
                        + Main.wins + ":" + Main.losses + " в вашу пользу!");
                return;
            }
        }

        if (playerHand.findValue() > 21) {
            Main.losses++;
            System.out.println("Вы проиграли раунд! Счет "
                    + Main.losses + ":" + Main.wins + " в пользу дилера");
            return;
        }

        System.out.println("Ход дилера");
        Card closedCard = dealer.showCard(1);
        System.out.println("Дилер открывает закрытую карту " + closedCard.toString());
        player.showDeck(player);
        dealer.showDeck(dealer, 1);

        while (dealerHand.findValue() <= 17) {
            dealerHand.takeFromDeck(deck);
            cardOpened = dealerHand.showLastCard();
            System.out.println("Дилер открывает карту " + cardOpened.toString());
            player.showDeck(player);
            dealer.showDeck(dealer, 1);
        }

        if (dealer.hasBlackJack()) {
            Main.losses++;
            System.out.println("Дилер получил блекджек. Вы проиграли раунд! Счет "
                    + Main.losses + ":" + Main.wins + " в пользу дилера");
            return;
        }

        if (Hand.didPlayerWin(playerHand, dealerHand) == 1) {
            Main.wins++;
            System.out.println("Вы выиграли раунд! Счет "
                    + Main.wins + ":" + Main.losses + " в вашу пользу!");
        } else if (Hand.didPlayerWin(playerHand, dealerHand) == -1) {
            Main.losses++;
            System.out.println("Вы проиграли раунд! Счет "
                    + Main.losses + ":" + Main.wins + " в пользу дилера");
        } else {
            System.out.println("Ничья! Счет " + Main.losses + ":" + Main.wins);
        }
    }
}
