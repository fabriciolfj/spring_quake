package br.com.game.log.quake.domain.facade.create;

import br.com.game.log.quake.domain.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

@Component
public class GameCreator {

    public Game createGame(Set<String> players, HashMap<String, Integer> kills, int totalKill) {
        return Game.builder().total(totalKill).players(players).kills(kills).build();
    }
}
