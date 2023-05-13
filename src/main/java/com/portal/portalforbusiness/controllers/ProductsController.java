package com.portal.portalforbusiness.controllers;

import com.portal.portalforbusiness.services.ProductService;
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

import static com.portal.portalforbusiness.utils.Utils.sendParametersJsonResponse;
import static com.portal.portalforbusiness.utils.Utils.updateUserInSession;

@Controller
public class ProductsController {
    private final SellerService sellerService = SellerService.getInstance();
    private final ProductService productService = ProductService.getInstance();


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        goToProductsPage(request, response);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goToProductsPage(request, response);
    }

    private void goToProductsPage(HttpServletRequest request, HttpServletResponse response) {
        String path = "products";

        String lookingName = request.getParameter("name");

        try {
            updateUserInSession(request);
            sellerService.getAllSellersWithoutProducts().ifPresent(sellers -> request.getSession().setAttribute("sellers", sellers));

            if (lookingName != null) {
                productService.findProductsByName(lookingName).ifPresent(productTypeParametersDto ->
                {
                    try {
                        sendParametersJsonResponse(productTypeParametersDto, response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                productService.getAllProductsWithJson().ifPresent(products -> request.getSession().setAttribute("products", products));
                request.getRequestDispatcher(JspHelper.getPath(path)).include(request, response);
            }

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
