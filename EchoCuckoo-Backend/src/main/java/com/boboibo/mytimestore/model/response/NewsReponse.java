package com.boboibo.mytimestore.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NewsReponse {
    String title;
    String preview;
    String content;
    String img;
}
