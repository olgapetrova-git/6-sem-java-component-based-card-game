package htwberlin.mau_mau.virtual_player_management.service;


import htwberlin.mau_mau.virtual_player_management.data.VirtualPlayer;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The type VirtualPlayerService implements implements operations relating to the virtual player.
 */
@Component
public class VirtualPlayerServiceImpl implements VirtualPlayerService {
    @Override
    public VirtualPlayer createVirtualPlayer() {
        String[] names = {"Hunter Thompson", "Raymond Chandler", "John Cheever", "O. Henry", "Tennessee Williams",
                "Dylan Thomas", "Dorothy Parker", "Truman Capote", "Edgar Allen Poe", "Charles Bukowski",
                "F. Scott Fitzgerald", "James Joyce", "Ernest Hemingway"};
        int randomNum = ThreadLocalRandom.current().nextInt(0, names.length);
        String name = names[randomNum];
        return new VirtualPlayer(name);
    }

}
