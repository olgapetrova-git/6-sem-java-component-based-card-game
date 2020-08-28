package htwberlin.mau_mau.rules_management.service;
import htwberlin.mau_mau.rules_management.data.GameRulesId;

/**
 * The type GameRulesFactory returns instance of RulesService object of specific type.
 */
public class GameRulesFactory {
    /**
     * Get game rules for different game variations.
     *
     * @param gameRulesId the game rules id
     * @return the game rules
     */
    public static RulesService getGameRules(GameRulesId gameRulesId){
        switch (gameRulesId) {
            case SPECIAL:return new RulesServiceSpecial();
            default:return new RulesServiceStandard();

        }
    }
}
