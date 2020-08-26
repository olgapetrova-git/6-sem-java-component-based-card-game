package htwberlin.mau_mau.player_management.data;

import htwberlin.mau_mau.card_management.data.Deck;

/**
 * The type Player.
 * Describes virtual or real player which takes part in the card game.
 */
public abstract class Player {
    /**
     * Name of the Player.
     */
    private String name;
    /**
     * The pack of cards held by this player.
     */
    private Deck hand;

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
