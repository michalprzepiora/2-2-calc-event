package com.kodilla.calcevent.controller;

import com.kodilla.calcevent.event.CalcOperationEvent;
import com.kodilla.calcevent.service.CalcService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")
public class CalcController implements ApplicationEventPublisherAware {
    private static final String ERROR = "Error: ";
    private final CalcService calcService;
    private ApplicationEventPublisher publisher;

    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping("/addition/{a}/{b}")
    public ResponseEntity<String> addition(@PathVariable double a, @PathVariable double b) {
        String result = Double.toString(calcService.addition(a, b));
        publisher.publishEvent(new CalcOperationEvent(this, new Throwable().getStackTrace()[0].getMethodName(), result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/subtraction/{a}/{b}")
    public ResponseEntity<String> subtraction(@PathVariable double a, @PathVariable double b) {
        String result = Double.toString(calcService.subtraction(a, b));
        publisher.publishEvent(new CalcOperationEvent(this, new Throwable().getStackTrace()[0].getMethodName(), result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/multiplication/{a}/{b}")
    public ResponseEntity<String> multiplication(@PathVariable double a, @PathVariable double b) {
        String result = Double.toString(calcService.multiplication(a, b));
        publisher.publishEvent(new CalcOperationEvent(this, new Throwable().getStackTrace()[0].getMethodName(), result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/division/{a}/{b}")
    public ResponseEntity<String> division(@PathVariable double a, @PathVariable double b) {
        ResponseEntity<String> responseEntity;
        String result;
        try {
            result = String.valueOf(calcService.division(a, b));
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ArithmeticException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            result = ERROR + e.getMessage();
                    }
        publisher.publishEvent(new CalcOperationEvent(this, new Throwable().getStackTrace()[0].getMethodName(), result));
        return responseEntity;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
