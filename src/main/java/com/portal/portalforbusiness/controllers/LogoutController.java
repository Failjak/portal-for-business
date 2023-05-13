package com.portal.portalforbusiness.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class LogoutController {
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        invalidateSession(request, response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        invalidateSession(request, response);
    }

    private void invalidateSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
