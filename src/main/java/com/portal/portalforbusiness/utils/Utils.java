package com.portal.portalforbusiness.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {
    private static final UserService userService = UserService.getInstance();

    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    public static void updateUserInSession(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        if (user != null) {
            userService.findUser(user).ifPresent(newUser -> request.getSession().setAttribute("user", newUser));
        }
    }

    public static void sendParametersJsonResponse(Object object, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String parameterJson = mapper.writeValueAsString(object);

        PrintWriter writer = response.getWriter();
        writer.append(parameterJson);
        response.setStatus(200);

    }

    public static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    public static Integer getEntityIdFromUrl(HttpServletRequest request) {
        try {
            String[] pathParams = request.getPathInfo().split("/");
            return Integer.parseInt(pathParams[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            return null;
        }
    }
}
