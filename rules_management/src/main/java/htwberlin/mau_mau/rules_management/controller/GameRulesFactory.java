package htwberlin.mau_mau.rules_management.controller;
import htwberlin.mau_mau.rules_management.model.GameRulesId;

/**
 * The type GameRulesFactory returns instance of GameRules object of specific type.
 */
public class GameRulesFactory {
    /**
     * Get game rules for different game variations.
     *
     * @param gameRulesId the game rules id
     * @return the game rules
     */
    public static GameRules getGameRules(GameRulesId gameRulesId){
        switch (gameRulesId) {
            case SPECIAL:return new SpecialRules();
            default:return new StandardRules();

        }
    }
}
