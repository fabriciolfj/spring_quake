package br.com.game.log.quake.api.controller;

import br.com.game.log.quake.domain.model.Game;
import br.com.game.log.quake.domain.service.FileAnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@RequestMapping("/upload")
@RestController
@RequiredArgsConstructor
public class AnaliseGameLogsController {

    private final FileAnalyzeService fileAnalyzeService;

    @PostMapping
    public LinkedHashMap<String, Game> upload(@RequestParam("file") final MultipartFile file) {
        return fileAnalyzeService.process(file);
    }
}
