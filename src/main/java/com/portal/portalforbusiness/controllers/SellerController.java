package com.portal.portalforbusiness.controllers;


import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.services.SellerService;
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
public class SellerController {

    private final SellerService sellerService = SellerService.getInstance();

    @RequestMapping(value = "/seller", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(JspHelper.getPath("registration")).include(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/seller", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SellerDto seller = SellerDto.builder().
                username(request.getParameter("username")).
                password(request.getParameter("password")).
                storeName(request.getParameter("storeName")).
                build();

        sellerService.saveSeller(seller);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onGetFail(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/seller/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onGetSuccess(UserDto seller, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("seller", seller);
        try {
            request.getRequestDispatcher(JspHelper.getPath("profile")).include(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
