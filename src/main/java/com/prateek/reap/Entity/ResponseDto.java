package com.prateek.reap.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {

    boolean status;
    String message;
    Object data;

}

