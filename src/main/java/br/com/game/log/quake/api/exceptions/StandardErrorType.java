package br.com.game.log.quake.api.exceptions;

import lombok.Getter;

@Getter
public enum StandardErrorType {
    SERVER_ERROR("Fail server"),
    MAX_SIZE_FILE("Size limit file "),
    PARAMETER_INVALID("Exceeded file size");

    private String title;

    StandardErrorType(final String title) {
        this.title = title;
    }
}
