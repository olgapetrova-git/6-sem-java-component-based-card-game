package htwberlin.mau_mau.rules_management.data;

import javax.persistence.Entity;

/**
 * The type RulesResultStandard contains validation result according to standard rules and related text message.
 */
@Entity
public class RulesResultStandard extends RulesResult {
    public RulesResultStandard(boolean success, String message) {
        super(success, message);
    }
}
