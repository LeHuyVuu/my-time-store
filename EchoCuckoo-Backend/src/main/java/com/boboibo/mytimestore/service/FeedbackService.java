package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.FeedBackMapper;
import com.boboibo.mytimestore.mapper.ProductMapper;
import com.boboibo.mytimestore.model.entity.Feedback;
import com.boboibo.mytimestore.model.entity.Order;
import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.enums.IsStatus;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.request.feedback.FeedbackRequest;
import com.boboibo.mytimestore.model.response.feedback.FeedbackResponse;
import com.boboibo.mytimestore.model.response.page.PageResponse;
import com.boboibo.mytimestore.model.response.product.ProductResponse;
import com.boboibo.mytimestore.repository.FeedBackRepository;
import com.boboibo.mytimestore.repository.ProductRepository;
import com.boboibo.mytimestore.utils.Common;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class FeedbackService {
    FeedBackRepository feedBackRepository;
    OrderService orderService;
    FeedBackMapper feedBackMapper;

    public FeedbackResponse createFeedbackByOrder(Long orderId, FeedbackRequest feedbackRequest) {
        // Kiểm tra nếu feedback đã tồn tại cho orderId
        if (feedBackRepository.existsByOrderOrderId(orderId)) {
            log.warn("Feedback already exists for orderId: {}", orderId);
            throw new AppException(ErrorCode.FEEDBACK_ALREADY_EXISTS, "Feedback for this order already exists.");
        }
        Order order = orderService.getOrderById(orderId);
        // Kiểm tra star rating
        if (feedbackRequest.getStar() < 0 || feedbackRequest.getStar() > 5) {
            throw new AppException(ErrorCode.STAR_INVALID, "Star rating must be between 0 and 5.");
        }

        // Lưu feedback
        Feedback feedback = feedBackMapper.toFeedback(feedbackRequest);
        feedback.setOrder(order);
        feedBackRepository.save(feedback);

        return feedBackMapper.toFeedbackResponse(feedback);
    }
    public FeedbackResponse updateFeedbackById(Long feedbackId, FeedbackRequest feedbackRequest) {
        Feedback feedback = getFeedBackById(feedbackId);
        feedback.setStar(feedbackRequest.getStar());
        feedback.setDescription(feedbackRequest.getDescription());
        feedBackRepository.save(feedback);
        return feedBackMapper.toFeedbackResponse(feedback);
    }
    public void deleteFeedbackByOrder(Long feedbackId) {
        Feedback feedback = getFeedBackById(feedbackId);
        feedBackRepository.delete(feedback);
    }
    private Feedback getFeedBackById(Long feedbackId) {
        Feedback feedback = feedBackRepository.findById(feedbackId).orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOT_EXITS));
        return feedback;
    }

}
