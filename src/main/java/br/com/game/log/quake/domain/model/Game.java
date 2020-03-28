package br.com.game.log.quake.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Set;

@Data
@Builder
public class Game {

    @JsonProperty("total_kills")
    private Integer total;
    private Set<String> players;
    private HashMap<String, Integer> kills;
}
