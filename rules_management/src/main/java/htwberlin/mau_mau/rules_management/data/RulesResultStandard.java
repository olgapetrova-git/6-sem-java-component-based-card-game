package htwberlin.mau_mau.rules_management.data;

import javax.persistence.Entity;

@Entity
public class RulesResultStandard extends RulesResult {
    public RulesResultStandard(boolean success, String message) {
        super(success, message);
    }
}
