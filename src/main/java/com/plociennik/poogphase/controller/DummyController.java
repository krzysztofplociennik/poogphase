package com.plociennik.poogphase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @RequestMapping(method = RequestMethod.GET, value = "hello")
    public String hello() {
        return "hello there!";
    }
}
