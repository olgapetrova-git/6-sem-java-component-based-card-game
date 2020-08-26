package htwberlin.mau_mau.real_player_management.controller;

import htwberlin.mau_mau.real_player_management.model.RealPlayer;
import org.springframework.stereotype.Component;

@Component
public class RealPlayerControllerImpl implements RealPlayerController{
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
       RealPlayer player = new RealPlayer(name);

        return player;
    }
}
