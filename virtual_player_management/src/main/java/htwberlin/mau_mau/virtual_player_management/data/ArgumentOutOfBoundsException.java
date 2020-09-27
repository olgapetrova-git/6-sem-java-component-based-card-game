package htwberlin.mau_mau.virtual_player_management.data;

public class ArgumentOutOfBoundsException extends Exception{

    public ArgumentOutOfBoundsException() {
        super("Error. Virtual player is not able to make a wish.");
    }
}
