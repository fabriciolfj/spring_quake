package br.com.game.log.quake.domain.model;

public enum TypeLine {

    USER("<world>"),
    END_GAME("ShutdownGame"),
    PLAYER_ENTERED("ClientUserinfoChanged"),
    KILLED(" killed"),
    KILL("Kill");

    private String pattern;

    TypeLine(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
