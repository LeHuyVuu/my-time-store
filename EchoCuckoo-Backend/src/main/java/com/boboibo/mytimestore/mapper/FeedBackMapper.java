package com.boboibo.mytimestore.mapper;

import com.boboibo.mytimestore.model.entity.Feedback;
import com.boboibo.mytimestore.model.request.feedback.FeedbackRequest;
import com.boboibo.mytimestore.model.response.feedback.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedBackMapper {
    @Mapping(source = "feedback.order.orderId", target = "orderId")
    FeedbackResponse toFeedbackResponse(Feedback feedback);
    @Mapping(target = "order",ignore = true)
    @Mapping(target = "feedbackId" , ignore = true)
    Feedback toFeedback(FeedbackRequest feedbackRequest);
}
