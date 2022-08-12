package com.krypton.citysearchapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @RequestMapping("")
    public @ResponseBody
    String greeting() {
        return "Hello, World";
    }

}