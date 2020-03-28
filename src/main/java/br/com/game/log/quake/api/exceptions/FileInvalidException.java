package br.com.game.log.quake.api.exceptions;

public class FileInvalidException extends RuntimeException {

    public FileInvalidException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public FileInvalidException(final String msg) {
        super(msg);
    }


}
