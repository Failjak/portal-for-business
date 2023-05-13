package com.portal.portalforbusiness.controllers;

import com.google.gson.Gson;

import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.dto.responses.IdDto;
import com.portal.portalforbusiness.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.portal.portalforbusiness.models.RoleType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.io.PrintWriter;

import static com.portal.portalforbusiness.utils.Utils.getBody;

@Controller
public class BuyProduct {

    private final ProductService productService = ProductService.getInstance();

    @RequestMapping(value = "/buy/*", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        Gson gson = new Gson();

        if (userDto == null) {
            String json = gson.toJson("Для совершения покупок необходимо авторизоваться");
            PrintWriter writer = response.getWriter();
            writer.append(json);
            response.setStatus(307);
            return;
        }

        if (userDto.getRole() != RoleType.BUYER) {
            String json = gson.toJson("Добавьте карту для совершения покупок");
            PrintWriter writer = response.getWriter();
            writer.append(json);
            response.setStatus(307);
            return;
        }

        String body = getBody(request);
        IdDto idDto = gson.fromJson(body, IdDto.class);

        try {
            productService.buyProduct(userDto, idDto.getId());

            String json = gson.toJson("Покупка совершена успешно!");
            PrintWriter writer = response.getWriter();
            writer.append(json);
            response.setStatus(201);
        } catch (Exception e) {
            String json = gson.toJson(e.getMessage());
            PrintWriter writer = response.getWriter();
            writer.append(json);
            response.setStatus(400);
        }
    }
}
