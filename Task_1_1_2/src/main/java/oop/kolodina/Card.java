package oop.kolodina;

/**
 * class to handle logic of cards' structure.
 */
public class Card {

    public final Mark mark;
    public final Rank rank;
    private final int value;

    public Card(Mark mark, Rank rank) {
        this.mark = mark;
        this.rank = rank;
        this.value = rank.assignValue();
    }

    /**
     * get value of card.
     *
     * @return value of the card
     */
    public int getValue() {
        return rank.assignValue();
    }

    /**
     * convert card to readable string.
     *
     * @return string consisting of card's mark, rank and value
     */
    public String toString() {
        return rank.getRuName() + " " + mark.getRuName() + " (" + value + ")";
    }

    /**
     * checks if card is Ace.
     *
     * @return true or false value
     */
    public Boolean isAce() {
        return this.rank.equals(Rank.ACE);
    }

    public enum Mark {
        HEART("червы"),
        DIAMOND("бубны"),
        CLUB("трефы"),
        SPADE("пики");

        private final String ruName;

        Mark(String ruName) {
            this.ruName = ruName;
        }

        public String getRuName() {
            return ruName;
        }
    }

    public enum Rank {
        TWO("Два", 2),
        THREE("Три", 3),
        FOUR("Четыре", 4),
        FIVE("Пять", 5),
        SIX("Шесть", 6),
        SEVEN("Семь", 7),
        EIGHT("Восемь", 8),
        NINE("Девять", 9),
        TEN("Десять", 10),
        JACK("Валет", 10),
        QUEEN("Дама", 10),
        KING("Король", 10),
        ACE("Туз", 11);

        private final String ruName;
        private final int value;

        Rank(String ruName, int value) {
            this.ruName = ruName;
            this.value = value;
        }

        public String getRuName() {
            return ruName;
        }

        private int assignValue() {
            return value;
        }
    }
}
