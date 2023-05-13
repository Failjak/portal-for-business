package com.portal.portalforbusiness.controllers;

import com.portal.portalforbusiness.dto.CardDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.services.CardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Random;


@Controller
public class CardController {
    private final CardService cardService = CardService.getInstance();

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        if (userDto == null) {
            try {
                response.sendRedirect("/login");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        CardDto card = CardDto.builder().
                ccn(request.getParameter("card__ccn")).
                month(request.getParameter("card__month")).
                year(request.getParameter("card__year")).
                cvv(request.getParameter("card__cvv")).
                balance(randFloat()).
                build();

        cardService.saveCard(card, userDto).ifPresent(cardDto -> {

//            request.getSession().setAttribute("user", user);
        });
        try {
            response.sendRedirect("/profile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Float randFloat() {
        Random rand = new Random();
        Float min = 1F;
        Float max = 10000F;
        return rand.nextFloat() * (max - min) + min;
    }
}
