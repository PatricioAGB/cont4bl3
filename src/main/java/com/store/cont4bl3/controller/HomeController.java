package com.store.cont4bl3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello home";
    }
    @GetMapping("/hola1")
    public String secured() {
        return "Funciona el CICD";
    }
}
