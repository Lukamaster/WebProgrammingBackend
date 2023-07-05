package com.webprogramming.backend.web;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.webprogramming.backend.model.WebProduct;
import com.webprogramming.backend.model.dto.WebProductDto;
import com.webprogramming.backend.repository.WebProductRepository;
import com.webprogramming.backend.stripe.PaymentDTO;
import com.webprogramming.backend.stripe.service.StripeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@SecurityRequirement(name = "securityScheme")
public class PaymentController {

    private final StripeService stripeService;
    private final WebProductRepository webProductRepository;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentDTO paymentDTO) throws StripeException {
        String chargeStatus = stripeService.executePayment(paymentDTO);
        return ResponseEntity.ok(chargeStatus);
    }

    @PostMapping
    public ResponseEntity<String> createSession(@RequestBody List<WebProductDto> webProducts) throws StripeException {

        List<SessionCreateParams.LineItem> listLineItem = new ArrayList<>();

        for (WebProductDto webProduct : webProducts) {

            WebProduct currentProd = webProductRepository.findWebProductByProductName(webProduct.getProductName());

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(webProduct.getQuantity())
                    .setPrice(stripeService.createStripeProduct(currentProd).getId())
                    .build();
            listLineItem.add(lineItem);
        }

        SessionCreateParams sessionParams =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:3000/" + "?success=true")
                        .setCancelUrl("http://localhost:3000/" + "?canceled=true")
                        .addAllLineItem(listLineItem)
                        .build();

        return ResponseEntity.ok(Session.create(sessionParams).getUrl());
    }
}
