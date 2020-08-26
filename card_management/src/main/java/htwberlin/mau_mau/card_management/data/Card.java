package htwberlin.mau_mau.card_management.data;

/**
 * The type Card.
 * Describes playing card.
 */
public class Card {
    /**
     * Suit of the card, the symbol on the card.
     */
    private final Suit suit;
    /**
     * Rank of the card, the value of the card.
     */
    private final Rank rank;

    /**
     * Instantiates a new playing card.
     *
     * @param suit the suit
     * @param rank the rank
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    /**
     * Gets card's suit.
     *
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets card's rank.
     *
     * @return the rank
     */
    public Rank getRank() {
        return rank;
    }
}
