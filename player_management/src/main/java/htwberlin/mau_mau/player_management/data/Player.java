package htwberlin.mau_mau.player_management.data;

import htwberlin.mau_mau.card_management.data.Deck;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Player.
 * Describes virtual or real player which takes part in the card game.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of the Player.
     */
    protected String name;
    /**
     * The pack of cards held by this player.
     */
    @OneToOne(targetEntity = Deck.class, cascade = CascadeType.ALL)
    protected Deck hand;

    public Player(){}

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name) {
        this.name = name;
        hand = new Deck();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets hand, i.e. object representing the deck of cards held by this player.
     *
     * @return the hand
     */
    public Deck getHand() {
        return hand;
    }

}
