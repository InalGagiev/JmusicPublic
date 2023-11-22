package ru.djt.jmusic.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestAuthController {
    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "Это незащищенные данные";
    }

    @GetMapping("/secured")
    public String securedData(){
        return "Это уже данные защищенные для обычного пользователя";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Данные только для админов";
    }
}
