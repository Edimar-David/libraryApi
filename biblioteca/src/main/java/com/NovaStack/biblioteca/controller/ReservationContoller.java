package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationContoller {
    @Autowired
    ReservationService service;
}
