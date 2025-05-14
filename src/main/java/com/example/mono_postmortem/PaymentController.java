package com.example.mono_postmortem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/initiate-payment")
    public Mono<PaymentResponse> generate(@RequestBody GenPaymentLink request) {
        String url = "https://webhook.site/492b54f8-9d09-44d0-aa27-7b67cce35cc8"; // set this to actual payment url that would match our Pyament response schema
        Mono<PaymentResponse> result = paymentService.buyData(url, request, "It is Well");

        result.subscribe(
                response -> {
                    System.out.println("[PaymentController][Subscribe]: " + response);
                });

        return result;

    }
}
