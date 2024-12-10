package com.boboibo.mytimestore.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NewsRequest {
    String title;
    String preview;
    String content;
    String img;
}
