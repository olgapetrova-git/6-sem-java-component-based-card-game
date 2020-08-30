package htwberlin.mau_mau.rules_management.service;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The type RulesProvider returns instance of RulesService object of specific type for different game variations.
 */
@Component
public class RulesProvider {

    @Autowired
    @Qualifier("RulesServiceStandard")
    private RulesService rulesServiceStandard;

    @Autowired
    @Qualifier("RulesServiceSpecial")
    private RulesService rulesServiceSpecial;

    private RulesService rulesService;

    public RulesService getRulesService() {
        return rulesService;
    }

    /**
     * Sets game rules for different game variations.
     *
     * @param gameRulesId the game rules id
     */
    public void chooseRules(GameRulesId gameRulesId){
        switch (gameRulesId) {
            case SPECIAL:  rulesService =  rulesServiceSpecial;
            break;
            default: rulesService = rulesServiceStandard;
     }
         }
}
