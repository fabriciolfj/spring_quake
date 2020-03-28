package br.com.game.log.quake.infrastructure;

import br.com.game.log.quake.api.exceptions.FileInvalidException;
import br.com.game.log.quake.api.exceptions.ServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;

public class FileConvertUtil {

    public static BufferedReader convertFile(final MultipartFile file) {
        return Optional.ofNullable(file)
                .filter(arq -> arq.getOriginalFilename().contains(".log"))
                .map(arq -> {
                    try {
                        return arq.getInputStream();
                    } catch (IOException e) {
                        throw new ServerErrorException(e.getMessage());
                    }
                })
                .map(reader -> new InputStreamReader(reader, Charset.forName("ISO-8859-1")))
                .map(stream -> new BufferedReader(stream))
                .orElseThrow(() -> new FileInvalidException("Format incorrect file."));
    }
}
