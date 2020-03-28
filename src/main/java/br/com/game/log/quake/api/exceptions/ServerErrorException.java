package br.com.game.log.quake.api.exceptions;

public class ServerErrorException extends RuntimeException {

    public ServerErrorException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public ServerErrorException(final String msg) {
        super(msg);
    }
}
