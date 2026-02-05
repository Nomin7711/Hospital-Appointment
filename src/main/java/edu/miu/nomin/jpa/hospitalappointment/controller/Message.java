package edu.miu.nomin.jpa.hospitalappointment.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Message {
    @GetMapping(path = "/hello/{message}")
    public String hello(@PathVariable String message, HttpSession session) {
        return "Hello " + message + " session id: " + session.getId();
    }
}
