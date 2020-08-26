package htwberlin.mau_mau.view_management.view;

public class NumberOutOfLimitsException extends Exception {

    public NumberOutOfLimitsException(int num, int min, int max) {
        super("Expected value between "+min+" and "+max+", but received "+num+".");
    }
}
