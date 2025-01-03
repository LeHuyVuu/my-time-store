package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.model.request.CartItemRequest;
import com.boboibo.mytimestore.model.request.feedback.FeedbackRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.model.response.feedback.FeedbackResponse;
import com.boboibo.mytimestore.service.FeedbackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class FeedbackController {
    FeedbackService feedbackService;
    @PostMapping("/{orderDetailId}")
    public ResponseEntity<ResponseObject> createFeedBackByOrderId(@PathVariable Long orderDetailId,@RequestBody FeedbackRequest feedbackRequest) {
        try {
            FeedbackResponse feedbackResponse = feedbackService.createFeedbackByOrder(orderDetailId,feedbackRequest);
            return ResponseObject.APIRepsonse(200, "FeedBack created successfully", HttpStatus.OK, feedbackResponse);
        } catch (AppException e) {
            return ResponseObject.APIRepsonse(403, "Can't create Feedback", HttpStatus.FORBIDDEN, null);
        }
    }
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<ResponseObject> deleteFeedbackById(@PathVariable Long feedbackId) {
        try {
            feedbackService.deleteFeedbackById(feedbackId);
            return ResponseObject.APIRepsonse(200, "Delete FeedBack successfully", HttpStatus.OK, null);
        } catch (AppException e) {
            return ResponseObject.APIRepsonse(403, "Can't delete Feedback", HttpStatus.FORBIDDEN, null);
        }
    }
    @PutMapping("/{feedbackId}")
    public ResponseEntity<ResponseObject> updateFeedBackByOrderId(@PathVariable Long feedbackId,@RequestBody FeedbackRequest feedbackRequest) {
        try {
            FeedbackResponse feedbackResponse = feedbackService.updateFeedbackById(feedbackId,feedbackRequest);
            return ResponseObject.APIRepsonse(200, "FeedBack update successfully", HttpStatus.OK, feedbackResponse);
        } catch (AppException e) {
            return ResponseObject.APIRepsonse(403, "Can't update Feedback", HttpStatus.FORBIDDEN, null);
        }
    }
//    @GetMapping("/{orderId}/total")
//    public ResponseEntity<ResponseObject> getTotalByProductId(@PathVariable Long productId) {
//        FeedbackResponse feedbackResponse = feedbackService
//    }

}
