package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.request.email.EmailRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<ResponseObject> sendEmail(@RequestBody EmailRequest emailRequest) {
        boolean isSent = sendEmailService.sendMailSender(emailRequest.getRecipient(), emailRequest.getBody(), emailRequest.getSubject());
        if (isSent) {
            return ResponseObject.APIRepsonse(200, "Email has been successfully sent. Please check your inbox.", HttpStatus.OK, "");
        } else {
            return ResponseObject.APIRepsonse(400, "Failed to send email.", HttpStatus.BAD_REQUEST, "");
        }
    }
}
