package com.boboibo.mytimestore.model.response.feedback;

import com.boboibo.mytimestore.model.response.user.UserResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackResponse {
    Long feedbackId;
    int star;
    String description;
     Long orderId; // Đảm bảo kiểu dữ liệu phù hợp
    float averageStar ;
    int number ;
    UserResponse userResponse ;
}