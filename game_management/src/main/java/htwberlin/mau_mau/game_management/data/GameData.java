package htwberlin.mau_mau.game_management.data;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.player_management.data.Player;
import java.util.ArrayList;

/**
 * The type Game data describes current game via game entities.
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
     * Current open card on the top of the playing stack
     */
    private Card openCard;

    /**
     * Status of current game
     */
    private GameStatus gameStatus;

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
        this.gameStatus = GameStatus.NORMAL;
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
     * Sets drawing stack.
     *
     * @param drawingStack the drawing stack
     */
    public void setDrawingStack(Deck drawingStack) {
        this.drawingStack = drawingStack;
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
     * Sets playing stack.
     *
     * @param playingStack the playing stack
     */
    public void setPlayingStack(Deck playingStack) {
        this.playingStack = playingStack;
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
     * Sets current player.
     *
     * @param currentPlayer the current player
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
     * Gets open card at the top of the playing stack.
     *
     * @return the open card at the top of the playing stack
     */
    public Card getOpenCard() {
        return openCard;
    }

    /**
     * Sets the open card at the top of the playing stack.
     *
     * @param openCard the open card at the top of the playing stack
     */
    public void setOpenCard(Card openCard) {
        this.openCard = openCard;
    }

    /**
     * Gets status of the current game.
     *
     * @return status of the current game.
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Sets status of the current game.
     *
     * @param gameStatus status of the current game.
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}