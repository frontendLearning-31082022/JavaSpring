package com.example.helloReactor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Alert {

    private Level level;
    private String orderBy;
    private Instant timeStamp;

    public enum Level{ YELLOW, ORANGE, RED, BLACK }
}
