package dev.dberenguer.dapr.checkout.controller;

import dev.dberenguer.dapr.checkout.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping(path = "/binding-cron")
    @ResponseStatus(HttpStatus.OK)
    public void checkout() {
        this.checkoutService.checkout();
    }

}
