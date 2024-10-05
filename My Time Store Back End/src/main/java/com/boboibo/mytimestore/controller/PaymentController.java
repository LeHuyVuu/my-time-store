package com.boboibo.mytimestore.controller;


import com.boboibo.mytimestore.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    private static String appointmentIdTemp = "";
    @GetMapping("/vn-pay")
    public ResponseEntity<Void> pay(HttpServletRequest request) {
        String paymentUrl = paymentService.createVnPayPayment(request);
        String appointmentId = request.getParameter("appointmentId");
        appointmentIdTemp = appointmentId.trim();
        System.out.println("Redirecting to: " + paymentUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(paymentUrl)).build();
    }
    @GetMapping("/vn-pay-callback")
    public ResponseEntity<Void> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        System.out.println("MY APPOINTMENT OH YEAH : " + appointmentIdTemp);
        if (status.equals("00")) {
            System.out.println("chay dum t ik, o day ne plz plz");
//            Invoice newInvoice = Invoice.builder()
//                    .paymentStatus(true)
//                    .totalPrice(Float.parseFloat(request.getParameter("vnp_Amount")))
//                    .createAt(LocalDateTime.now())
//                    .appointment(appointmentRepository.findAppointmentById(appointmentIdTemp))
//                    .build();
//            invoiceRepository.save(newInvoice);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:3000/booking/paymentsuccess")).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:3000/booking/paymentfailed")).build();
        }
    }
}