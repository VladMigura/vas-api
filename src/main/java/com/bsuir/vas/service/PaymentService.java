package com.bsuir.vas.service;

import com.bsuir.vas.model.PaymentModel;

public interface PaymentService {

    PaymentModel createPayments(PaymentModel paymentModel);
    void deletePayments(PaymentModel paymentModel);
}
