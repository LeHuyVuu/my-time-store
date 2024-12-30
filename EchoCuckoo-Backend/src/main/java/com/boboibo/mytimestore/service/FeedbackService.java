package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.FeedBackMapper;
import com.boboibo.mytimestore.mapper.ProductMapper;
import com.boboibo.mytimestore.model.entity.Feedback;
import com.boboibo.mytimestore.model.entity.Order;
import com.boboibo.mytimestore.model.entity.OrderDetail;
import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.enums.IsStatus;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.request.feedback.FeedbackRequest;
import com.boboibo.mytimestore.model.response.feedback.FeedbackResponse;
import com.boboibo.mytimestore.model.response.page.PageResponse;
import com.boboibo.mytimestore.model.response.product.ProductResponse;
import com.boboibo.mytimestore.repository.FeedBackRepository;
import com.boboibo.mytimestore.repository.OrderRepository;
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
    ProductService productService;
    OrderDetailService orderDetailService;
    private final OrderRepository orderRepository;

    public FeedbackResponse createFeedbackByOrder(Long orderDetailId, FeedbackRequest feedbackRequest) {
        // Kiểm tra nếu feedback đã tồn tại cho orderId
        if (feedBackRepository.existsByOrderDetail_OrderDetailId(orderDetailId)) {
            log.warn("Feedback already exists for orderId: {}", orderDetailId);
            throw new AppException(ErrorCode.FEEDBACK_ALREADY_EXISTS, "Feedback for this order already exists.");
        }
//        OrderDetail order = orderService.getOrderById(orderDetailId);
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        // Kiểm tra star rating
        if (feedbackRequest.getStar() < 0 || feedbackRequest.getStar() > 5) {
            throw new AppException(ErrorCode.STAR_INVALID, "Star rating must be between 0 and 5.");
        }
        // Lưu feedback
        Feedback feedback = feedBackMapper.toFeedback(feedbackRequest);
        feedback.setOrderDetail(orderDetail);
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
@Transactional
public void deleteFeedbackById(Long feedbackId) {
    Feedback feedback = getFeedBackById(feedbackId);

    // Gỡ liên kết với OrderDetail trước khi xóa
    if (feedback.getOrderDetail() != null) {
        feedback.getOrderDetail().setFeedback(null);
    }

    feedBackRepository.delete(feedback);
    feedBackRepository.flush(); // Đồng bộ hóa ngay lập tức với cơ sở dữ liệu
}
    private Feedback getFeedBackById(Long feedbackId) {
        Feedback feedback = feedBackRepository.findById(feedbackId).orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOT_EXITS));
        return feedback;
    }
//    public FeedbackResponse getTotalRatingByProductId(Long productId) {
//        Product product = productService.getProductById(productId);
//        int count = 0 ;
//        float sumStar = 0 ;
//        List<Order> orderList = orderService.findAllByService_ServiceId(service.getServiceId());
//        if (!appointments.isEmpty()) {
//
//            for (Appointment appointment : appointments) {
//                log.info(appointment.getAppointmentId());
//                Feedback feedback = feedbackRepository.findByAppointment_AppointmentId(appointment.getAppointmentId());
//                if (feedback != null && appointment.getCustomer().getUser().isStatus()) {
//                    sumStar += feedback.getStar();
//                    count++;
//                }
//            }
//        }else {
//            throw new AppException(
//                    ErrorCode.APPOINTMENT_NOT_FOUND.getCode(),
//                    ErrorCode.APPOINTMENT_NOT_FOUND.getMessage(),
//                    HttpStatus.NOT_FOUND);
//        }
//        float averageStar = (count > 0) ? (float) sumStar / count : 0;
//        FeedbackResponse feedbackResponse = FeedbackResponse.builder()
//                .averageStar(averageStar)
//                .number(count)
//                .build();
//
//        return feedbackResponse ;
//    }

}
