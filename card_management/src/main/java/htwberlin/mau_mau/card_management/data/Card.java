package htwberlin.mau_mau.card_management.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * The type Card.
 * Describes playing card.
 */
@Entity
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Suit of the card, the symbol on the card.
     */
    private final Suit suit;
    /**
     * Rank of the card, the value of the card.
     */
    private final Rank rank;

    public Card() {
        suit = Suit.HEARTS;
        rank = Rank.ACE;
    }

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
