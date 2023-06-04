package com.example.helloReactor.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
public class Message {
    @Id
    private Long id;
    private String data;
}
