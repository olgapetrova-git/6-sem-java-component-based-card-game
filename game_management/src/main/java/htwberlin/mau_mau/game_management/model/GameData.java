package htwberlin.mau_mau.game_management.model;

import htwberlin.mau_mau.card_management.model.Deck;
import htwberlin.mau_mau.rules_management.model.GameRulesId;
import htwberlin.mau_mau.player_management.model.Player;
import java.util.ArrayList;

/**
 * The type Game data.
 * Describes current game via game entities.
 */
public class GameData {
    /**
     * An object of the drawing stack of cards. Represents an undealt portion of the deck of cards, face down, which are
     * left over after setting up the game and will be used in the rest of the game.
     */
    private Deck drawingStack;

    /**
     * An object that represents the playing stack, i.e. the pack of playing cards, which were already played by players
     * and put face up on top of each other.
     */
    private Deck playingStack;

    /**
     * List of players
     */
    private ArrayList<Player> players;

     /**
     * Current player
     */
    private Player currentPlayer;

    /**
     * Game Rules Id
     */
    private GameRulesId gameRulesId;

    /**
     * Flag, if player has won.
      */
    private boolean isWin;

    /**
     * Instantiates a new Game data.
     *
     * @param drawingStack the drawing stack, an undealt portion of the deck of cards
     * @param playingStack the playing stack, the pack of cards, which were already played
     * @param players      list of players
     * @param gameRulesId  the game rules id
     */
    public GameData(Deck drawingStack, Deck playingStack, ArrayList<Player> players, GameRulesId gameRulesId) {
        this.drawingStack = drawingStack;
        this.playingStack = playingStack;
        this.players = players;
        this.gameRulesId = gameRulesId;
        isWin = false;
    }

    /**
     * Gets drawing stack.
     *
     * @return the drawing stack
     */
    public Deck getDrawingStack() {
        return drawingStack;
    }


    /**
     * Gets playing stack.
     *
     * @return the playing stack
     */
    public Deck getPlayingStack() {
        return playingStack;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets Game Rules Id.
     *
     * @return the game rules id
     */
    public GameRulesId getGameRulesId() {
        return gameRulesId;
    }


    /**
     * Get win boolean.
     *
     * @return the boolean, if player has won.
     */
    public boolean isWin() {
        return isWin;
    }

    /**
     * Sets win.
     *
     * @param win the win
     */
    public void setWin(boolean win) {
        isWin = win;
    }
}