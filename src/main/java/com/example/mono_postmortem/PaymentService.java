package com.example.mono_postmortem;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public final WebClient webClient;
    public static final String TEST_KEY = "is-this-playing-what-kind-0f-playing-is-this";

    // why are we returining a generic type here?
    //
    // if buyData is suppose to be returning something it shoould
    // be returning our schema, which means PaymentResponse
    public Mono<PaymentResponse> buyData(String urlAddress, GenPaymentLink reqBody, String encodedString) {
        return webClient.post()
                .uri(urlAddress)
                .body(Mono.just(reqBody), GenPaymentLink.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(PaymentResponse.class);
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return clientResponse.bodyToMono(String.class).flatMap(errorMessage -> Mono
                                .error(new HttpServerErrorException(clientResponse.statusCode(), errorMessage)));
                    } else {
                        return Mono.error(new RuntimeException(
                                "Unhandled exception" + clientResponse.statusCode()));
                    }
                });
    }
}
