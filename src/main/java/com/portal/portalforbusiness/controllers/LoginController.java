package com.portal.portalforbusiness.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = "/v1/login", method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }
}
