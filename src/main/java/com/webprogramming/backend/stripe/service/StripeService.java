package com.webprogramming.backend.stripe.service;

import com.webprogramming.backend.stripe.PaymentDTO;
import com.stripe.exception.StripeException;

public interface StripeService {

    String executePayment(PaymentDTO paymentDTO) throws StripeException;
}
