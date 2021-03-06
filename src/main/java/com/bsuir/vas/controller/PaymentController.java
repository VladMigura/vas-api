package com.bsuir.vas.controller;

import com.bsuir.vas.model.PaymentModel;
import com.bsuir.vas.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentModel createPayments(@RequestBody PaymentModel paymentModel) {

        return paymentService.createPayments(paymentModel);
    }

    @DeleteMapping("/payments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayments(@RequestBody PaymentModel paymentModel) {

        paymentService.deletePayments(paymentModel);
    }
}
