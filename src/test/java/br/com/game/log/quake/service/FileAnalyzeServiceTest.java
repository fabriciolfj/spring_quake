package br.com.game.log.quake.service;


import br.com.game.log.quake.api.exceptions.FileInvalidException;
import br.com.game.log.quake.domain.facade.create.GameCreator;
import br.com.game.log.quake.domain.model.Game;
import br.com.game.log.quake.domain.service.FileAnalyzeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileAnalyzeServiceTest {

    @Mock
    private GameCreator gameCreator;

    @InjectMocks
    private FileAnalyzeService fileAnalyzeService;

    @Test
    public void apurarArquivoInvalido() throws IOException {
        var file = new File("src/main/resources/file/games_formato_invalido.png");
        var multipart = new MockMultipartFile("file", file.getName(),"text/plain",  new FileInputStream(file));
        var game = createGameEmpty();
        lenient().when(gameCreator.createGame(anySet(), any(HashMap.class), anyInt())).thenReturn(game);

        assertThrows(FileInvalidException.class, () -> fileAnalyzeService.process(multipart));
    }

    @Test
    public void apurarArquivoVazio() throws IOException {
        //cenário
        var file = new File("src/main/resources/file/games_vazio.log");
        var multipart = new MockMultipartFile("file", file.getName(),"text/plain",  new FileInputStream(file));
        var game = createGameEmpty();
        lenient().when(gameCreator.createGame(anySet(), any(HashMap.class), anyInt())).thenReturn(game);

        //processo
        var result = fileAnalyzeService.process(multipart);

        assertTrue(result.isEmpty());
    }

    @Test
    public void apurarCorretamenteLogDoGame() throws IOException {
        //cenário
        var file = new File("src/main/resources/file/games.log");
        var multipart = new MockMultipartFile("file", file.getName(),"text/plain",  new FileInputStream(file));
        var game = createGame();
        when(gameCreator.createGame(anySet(), any(HashMap.class), anyInt())).thenReturn(game);

        //processo
        var result = fileAnalyzeService.process(multipart);

        //verificação
        var resultGame = result.get("game_1");
        assertEquals(game.getPlayers().size(),resultGame.getPlayers().size());
        assertEquals(game.getKills().size(), resultGame.getKills().size());
        assertEquals(game.getTotal(), 105);
    }

    @Test
    public void apurarCorretamenteLogDoGameSemPlayerWorld() throws IOException {
        //cenário
        var file = new File("src/main/resources/file/games.log");
        var multipart = new MockMultipartFile("file", file.getName(),"text/plain",  new FileInputStream(file));
        var game = createGame();
        when(gameCreator.createGame(anySet(), any(HashMap.class), anyInt())).thenReturn(game);

        //processo
        var result = fileAnalyzeService.process(multipart);

        //verificação
        var resultGame = result.get("game_1");
        assertFalse(game.getPlayers().contains("world"));
    }

    private Game createGameEmpty() {
        return Game.builder().build();
    }

    private Game createGame() {
        Set<String> players = new HashSet<>();
        players.add("Dono da Bola");
        players.add("Zeh");
        players.add("Assasinu Credi");
        players.add("Isgalamido");

        HashMap<String, Integer> kills = new HashMap<>();
        kills.put("Isgalamido", 19);
        kills.put("Dono da Bola", 13);
        kills.put("Zeh", 20);
        kills.put("Isgalamido", 13);

        return Game.builder().players(players).total(105).kills(kills).build();
    }
}
