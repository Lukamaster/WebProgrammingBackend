package com.webprogramming.backend.stripe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.webprogramming.backend.config.PurchaseConfig;
import com.webprogramming.backend.service.UserService;
import com.webprogramming.backend.stripe.PaymentDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeServiceImpl implements StripeService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService;
    private final PurchaseConfig purchaseConfig;

    @Override
    @Transactional
    public String executePayment(PaymentDTO paymentDTO) {
        Stripe.apiKey = purchaseConfig.getSecretApiKey();
        Customer customer = createCustomer(paymentDTO);
        ChargeCreateParams chargeCreateParams = getChargeCreateParams(paymentDTO, customer);
        try {
            Charge charge = Charge.create(chargeCreateParams);
            return charge.getStatus();
        } catch (StripeException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private ChargeCreateParams getChargeCreateParams(PaymentDTO paymentDTO, Customer customer) {
        return ChargeCreateParams.builder().setCustomer(customer.getId()).setReceiptEmail(customer.getEmail())
                .setCurrency(paymentDTO.getCurrency().toString()).setAmount(paymentDTO.getAmount().longValue()).build();
    }

    private Customer createCustomer(PaymentDTO paymentDTO) {
        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder().
                setName(paymentDTO.getCustomerFullName()).setEmail(paymentDTO.getEmail()).
                setSource(purchaseConfig.getCardToken()).build();
        try {
            return Customer.create(customerCreateParams);
        } catch (StripeException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }
}
