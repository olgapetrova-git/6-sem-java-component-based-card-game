package htwberlin.mau_mau.real_player_management.data;

import htwberlin.mau_mau.player_management.data.Player;

import javax.persistence.Entity;

/**
 * The type Real player.
 * Describes a real player, operated by a human.
 */
@Entity
public class RealPlayer extends Player {
    /**
     * Flag if Player has pressed button 'Mau!' when it is his turn and he only has two cards
     */
    private boolean saidMau;

    /**
     * Flag if Player has pressed button "Mau-Mau"
     */
    private boolean saidMauMau;

    /**
     * Instantiates a new Real player.
     *
     * @param name the player's name, typed by the player and returned from View
     */
    public RealPlayer(String name) {
        super(name);
        saidMau = false;
        saidMauMau = false;
    }
    /**
     * Gets value of saidMau boolean.
     *
     * @return the boolean
     */
    public boolean isSaidMau() {
        return saidMau;
    }

    /**
     * Sets value of saidMau boolean.
     *
     * @param saidMau the boolean
     */
    public void setSaidMau(boolean saidMau) {
        this.saidMau = saidMau;
    }

    /**
     * Gets value of saidMauMau boolean.
     *
     * @return the boolean
     */
    public boolean isSaidMauMau() {
        return saidMauMau;
    }

    /**
     * Sets value of saidMauMau boolean
     *
     * @param saidMauMau the boolean
     */
    public void setSaidMauMau(boolean saidMauMau) {
        this.saidMauMau = saidMauMau;
    }
}
