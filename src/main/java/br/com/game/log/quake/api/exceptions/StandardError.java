package br.com.game.log.quake.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError {

    private Integer status;
    private String title;
    private String detail;
    private String userMessage;
    private OffsetDateTime timestamp;
}
