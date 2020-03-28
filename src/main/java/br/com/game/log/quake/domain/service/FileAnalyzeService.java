package br.com.game.log.quake.domain.service;

import br.com.game.log.quake.api.exceptions.ServerErrorException;
import br.com.game.log.quake.domain.facade.create.GameCreator;
import br.com.game.log.quake.domain.model.Game;
import br.com.game.log.quake.domain.model.TypeLine;
import br.com.game.log.quake.infrastructure.FileConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FileAnalyzeService {

    private final GameCreator gameCreator;

    private Set<String> players = new LinkedHashSet();
    private Integer indexGame = 1, totalKill = 0;
    private HashMap<String, Integer> kills = new LinkedHashMap();


    public LinkedHashMap<String, Game> process(final MultipartFile file) {
        var reader = FileConvertUtil.convertFile(file);
        var data = new LinkedHashMap<String, Game>();

        try{
            var line = reader.readLine();

            while (Objects.nonNull(line)) {
                if(line.contains(TypeLine.PLAYER_ENTERED.getPattern())){
                    players.add(line.split("n\\\\")[1].split("\\\\t")[0]);
                }

                if(line.contains(TypeLine.KILL.getPattern()) && !players.isEmpty()) {
                    totalKill++;
                    accountKillPlayers(line);
                }

                if(line.contains(TypeLine.END_GAME.getPattern())) {
                   var game = gameCreator.createGame(players, kills, totalKill);
                   data.put("game_" + indexGame, game);
                   resetGame();
                }

                line = reader.readLine();
            }

            return data;
        } catch (Exception e) {
            throw new ServerErrorException(String.format("Fail process file. Details: %s", e.getMessage()));
        }
    }

    private void resetGame() {
        kills = new LinkedHashMap<>();
        players = new LinkedHashSet<>();
        totalKill = 0;
        indexGame++;
    }

    private void accountKillPlayers(String line) {
        players.stream().forEach(player -> {
            if(line.contains(player) && line.contains(TypeLine.USER.getPattern())) {
                var value = kills.get(player) != null ? kills.get(player) : 0;
                value--;
                kills.put(player, value);
            }

            if(line.contains(player + TypeLine.KILLED.getPattern()) && !line.contains(TypeLine.USER.getPattern())) {
                var value = kills.get(player) != null ? kills.get(player) : 0;
                value++;
                kills.put(player, value);
            }
        });
    }
}
