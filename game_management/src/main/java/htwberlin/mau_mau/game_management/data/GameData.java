package htwberlin.mau_mau.game_management.data;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultStandard;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game data describes current game state via game entities.
 */
@Entity
public class GameData {


    /**
     * Unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * An object of the drawing stack of cards. Represents a not yet dealt portion of the deck of cards, face down, which are
     * left over after setting up the game and will be used in the rest of the game.
     */
    @OneToOne(targetEntity = Deck.class, cascade = CascadeType.ALL)
    private Deck drawingStack;

    /**
     * An object that represents the playing stack, i.e. the pack of playing cards, which were already played by players
     * and put face up on top of each other.
     */
    @OneToOne(targetEntity = Deck.class, cascade = CascadeType.ALL)
    private Deck playingStack;

    /**
     * List of players
     */
    @OneToMany (cascade = CascadeType.ALL)
    private List<Player> players;

    /**
     * Current player
     */
    @OneToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    private Player currentPlayer;

    /**
     * Game Rules Id
     */
    @Enumerated(EnumType.STRING)
    private GameRulesId gameRulesId;

    /**
     * Current open card on the top of the playing stack
     */
    @OneToOne(targetEntity = Card.class, cascade = CascadeType.ALL)
    private Card openCard;

    /**
     * Status of current game
     */
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    /**
     * Result and status of last rules validation
     */
    @OneToOne(targetEntity = RulesResult.class, cascade = CascadeType.ALL)
    private RulesResult rulesResult;

    /**
     * Instantiates a new Game data object.
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

        // instantiate RulesResult as standard by default
        // it will be changed later if player selects special rules
        this.rulesResult = new RulesResultStandard(false, "");
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
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
    public List<Player> getPlayers() {
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

    /**
     * Gets rules result object.
     *
     * @return the rules result
     */
    public RulesResult getRulesResult() {
        return rulesResult;
    }

    /**
     * Sets rules result.
     *
     * @param rulesResult the rules result
     */
    public void setRulesResult(RulesResult rulesResult) {
        this.rulesResult = rulesResult;
    }
}