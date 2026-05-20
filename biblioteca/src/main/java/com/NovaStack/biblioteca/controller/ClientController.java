package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;
}
