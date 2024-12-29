package com.boboibo.mytimestore.model.request.feedback;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class FeedbackRequest {
    Long feedbackId;
    int star;
    String description;
    Long orderId;
}