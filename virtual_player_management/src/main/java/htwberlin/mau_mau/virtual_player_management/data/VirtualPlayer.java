package htwberlin.mau_mau.virtual_player_management.data;

import htwberlin.mau_mau.card_management.data.Suit;
import htwberlin.mau_mau.player_management.data.Player;

import javax.persistence.Entity;

/**
 * The type Virtual player.
 * Describes a player, created and operated by the computer.
 */
@Entity
public class VirtualPlayer extends Player {
    /**
     * Instantiates a new Virtual player.
     *
     * @param name the name
     */
    public VirtualPlayer(String name) {
        super(name);
    }

    public Suit getWish() throws ArgumentOutOfBoundsException {
        try{
            return hand.peek().getSuit();
        } catch (Exception e){
            throw new ArgumentOutOfBoundsException();
        }

    }
}
