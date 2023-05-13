package com.portal.portalforbusiness.controllers;

import com.portal.portalforbusiness.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RegistrationController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspHelper.getPath("registration")).include(request, response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equals("seller")) {
            request.getServletContext().getRequestDispatcher("/seller").forward(request, response);
        } else if(state.equals("user")) {
            request.getServletContext().getRequestDispatcher("/user").forward(request, response);
        }
    }
}
