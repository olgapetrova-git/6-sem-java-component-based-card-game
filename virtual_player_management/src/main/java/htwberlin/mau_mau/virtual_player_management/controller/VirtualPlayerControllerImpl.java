package htwberlin.mau_mau.virtual_player_management.controller;


import htwberlin.mau_mau.virtual_player_management.model.VirtualPlayer;
import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class VirtualPlayerControllerImpl implements VirtualPlayerController {
    @Override
    public VirtualPlayer createVirtualPlayer() {
        //TODO: rewrite name generation, so that the name is unique, i.e names are stored in ArrayList and if choosen,
        // the name is removed from the list; that way we avoid duplication like three Bukowski at the game table
        String[] names = {"Hunter Thompson", "Raymond Chandler", "John Cheever", "O. Henry", "Tennessee Williams",
                "Dylan Thomas", "Dorothy Parker", "Truman Capote", "Edgar Allen Poe", "Charles Bukowski",
                "F. Scott Fitzgerald", "James Joyce", " Ernest Hemingway"};//Top 15 Great Alcoholic Writers
        int randomNum = ThreadLocalRandom.current().nextInt(0, names.length);
        String name = names[randomNum];
        return new VirtualPlayer(name);
    }

}
