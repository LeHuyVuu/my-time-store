package com.boboibo.mytimestore.mapper;

import com.boboibo.mytimestore.model.entity.Feedback;
import com.boboibo.mytimestore.model.request.feedback.FeedbackRequest;
import com.boboibo.mytimestore.model.response.feedback.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedBackMapper {
    // Ánh xạ `orderDetail.orderDetailId` tới `orderDetailId` trong `FeedbackResponse`
    @Mapping(source = "feedback.orderDetail.orderDetailId", target = "orderDetailId")
    FeedbackResponse toFeedbackResponse(Feedback feedback);

    // Bỏ qua trường `orderDetail` khi ánh xạ từ request sang entity
    @Mapping(target = "orderDetail", ignore = true)
    @Mapping(target = "feedbackId", ignore = true)
    Feedback toFeedback(FeedbackRequest feedbackRequest);
}
