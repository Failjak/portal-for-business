package com.portal.portalforbusiness.controllers;

import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.models.RoleType;
import com.portal.portalforbusiness.services.UserService;
import com.portal.portalforbusiness.utils.ImageUploadHelper;
import com.portal.portalforbusiness.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.util.Optional;

import static com.portal.portalforbusiness.utils.Utils.getEntityIdFromUrl;

@Controller
public class UserController {

    private final UserService userService = UserService.getInstance();

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getEntityIdFromUrl(request);
        UserDto user = UserDto.builder().
                id(id).
                build();

        if (request.getParameter("soft").equals("True")) {
            userService.blockUser(user).
                    ifPresentOrElse(userDto -> response.setStatus(204), () -> response.setStatus(404));
        } else {
            userService.deleteUser(user).
                    ifPresentOrElse(userDto -> response.setStatus(204), () -> response.setStatus(404));
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = getEntityIdFromUrl(request);

        if (id != null) {
            UserDto userDto = UserDto.builder().id(id).build();
            Optional<UserDto> res = userService.findUser(userDto);
            res.ifPresentOrElse(user -> onGetSuccess(user, request, response), () -> onGetFail(request, response));
        }

        if (request.getParameter("change") != null) {
            try {
                if (request.getSession().getAttribute("user") == null)
                    response.sendRedirect("/login");
                else
                    request.getRequestDispatcher(JspHelper.getPath("edit_profile")).include(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        } else if (request.getParameter("upgrade") != null) {
            try {
                if (request.getSession().getAttribute("user") == null)
                    response.sendRedirect("/login");
                else
                    request.getRequestDispatcher(JspHelper.getPath("upgrade_profile")).include(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        if (userDto == null)
            return;

        String imagePath = ImageUploadHelper.uploadImage(request.getPart("photo"), "");
        if (imagePath.isBlank()) {
            imagePath = userDto.getImagePath();
        }

        UserDto newUserDto = UserDto.builder().
                username(request.getParameter("username")).
                password(request.getParameter("password")).
                imagePath(imagePath).build();

        userService.updateUser(userDto, newUserDto).ifPresent(modifiedUserDto ->
                request.getSession().setAttribute("user", modifiedUserDto)
        );
        try {
            response.sendRedirect("/profile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("_method") != null && request.getParameter("_method").equals("put")) {
            doPut(request, response);
            return;
        }

        UserDto user = UserDto.builder().
                username(request.getParameter("username")).
                password(request.getParameter("password")).
                isActive(Boolean.TRUE).
                role(RoleType.USER).
                build();

        userService.saveUser(user);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onGetFail(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/user/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onGetSuccess(UserDto user, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", user);
        try {
            request.getRequestDispatcher(JspHelper.getPath("profile")).include(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
