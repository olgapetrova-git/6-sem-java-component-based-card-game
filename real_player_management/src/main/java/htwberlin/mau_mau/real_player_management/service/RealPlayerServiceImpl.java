package htwberlin.mau_mau.real_player_management.service;

import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import org.springframework.stereotype.Component;

/**
 * The type Real player service implements operations relating to the real player's attributes.
 *
 */
@Component
public class RealPlayerServiceImpl implements RealPlayerService {
    @Override
    public boolean isSaidMau(RealPlayer player) {
        return player.isSaidMau();
    }

    @Override
    public void setSaidMau(boolean saidMau, RealPlayer player) {
        player.setSaidMau(saidMau);
    }

    @Override
    public boolean isSaidMauMau(RealPlayer player) {
        return player.isSaidMauMau();
    }

    @Override
    public void setSaidMauMau(boolean saidMauMau, RealPlayer player) {
        player.setSaidMauMau(saidMauMau);
    }

    @Override
    public RealPlayer createRealPlayer(String name) {
        return new RealPlayer(name);
    }
}
