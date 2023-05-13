package com.portal.portalforbusiness.controllers;


import com.portal.portalforbusiness.dto.ProductDto;
import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.services.ParameterService;
import com.portal.portalforbusiness.services.ProductService;
import com.portal.portalforbusiness.services.SellerService;
import com.portal.portalforbusiness.services.UserService;
import com.portal.portalforbusiness.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.util.List;

import static com.portal.portalforbusiness.models.RoleType.BUYER;
import static com.portal.portalforbusiness.utils.Utils.updateUserInSession;


@Controller
public class ProfileController{
    private final UserService userService = UserService.getInstance();
    private final SellerService sellerService = SellerService.getInstance();
    private final ProductService productService = ProductService.getInstance();
    private final ParameterService parameterService = ParameterService.getInstance();

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goToProfilePage(request, response);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goToProfilePage(request, response);
    }

    private void goToProfilePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Object seller = request.getSession().getAttribute("seller");
            UserDto user = (UserDto) request.getSession().getAttribute("user");
            if (seller != null) {
                productService.getProductsDtoBySeller((SellerDto) seller).ifPresent(
                        products -> request.getSession().setAttribute("products", products)
                );
                String path = "seller_profile";
                request.getRequestDispatcher(JspHelper.getPath(path)).include(request, response);
            } else if (user != null) {
                String path;
                switch (user.getRole()) {
                    case USER, BUYER -> {
                        path = "profile";
                        updateUserInSession(request);
                        if (user.getRole() == BUYER) {
                            List<ProductDto> boughtProducts = productService.getBoughtProductsByUser(user);
                            request.getSession().setAttribute("boughtProducts", boughtProducts);
                        }
                    }
                    case ADMIN -> {
                        userService.getAllUsers(1).ifPresent(
                                usersDto -> request.getSession().setAttribute("users", usersDto)
                        );
                        sellerService.getAllSellersWithoutProducts().ifPresent(
                                sellersDto -> request.getSession().setAttribute("sellers", sellersDto)
                        );
                        parameterService.getAllParameters().ifPresent(
                                parametersDto -> request.getSession().setAttribute("parameters", parametersDto)
                        );
                        path = "admin_profile";
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
                }

                request.getRequestDispatcher(JspHelper.getPath(path)).include(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
