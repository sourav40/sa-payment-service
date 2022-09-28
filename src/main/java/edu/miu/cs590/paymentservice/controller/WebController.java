package edu.miu.cs590.paymentservice.controller;

import edu.miu.cs590.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class WebController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam(value = "code") String code) {
        paymentService.pushPaymentResponseToPaymentProducer(code, null, 0.0, 0.0, null);
        return "checkout";
    }
}
