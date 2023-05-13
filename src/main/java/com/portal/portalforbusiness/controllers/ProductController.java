package com.portal.portalforbusiness.controllers;

import com.portal.portalforbusiness.dto.ProductDto;
import com.portal.portalforbusiness.dto.SellerDto;
import com.portal.portalforbusiness.dto.UserDto;
import com.portal.portalforbusiness.models.ProductType;
import com.portal.portalforbusiness.services.ProductService;

import com.portal.portalforbusiness.utils.ImageUploadHelper;
import com.portal.portalforbusiness.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.util.Currency;

import static com.portal.portalforbusiness.utils.Utils.getEntityIdFromUrl;

@Controller
public class ProductController {
    private final ProductService productService = ProductService.getInstance();

    @RequestMapping(value = "/product/*", method = RequestMethod.POST)
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SellerDto sellerDto = (SellerDto) request.getSession().getAttribute("seller");

        if (sellerDto == null) {
            try {
                response.sendRedirect("/login");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Integer id = getEntityIdFromUrl(request);
        ProductDto product = ProductDto.builder().
                id(id).
                build();

        productService.delete(product, sellerDto).
                ifPresentOrElse(productDto -> response.setStatus(204), () -> response.setStatus(404));
    }

    @RequestMapping(value = "/product/*", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("seller") == null) {
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Integer id = getEntityIdFromUrl(request);
        if (id == null) {
            try {
                request.getRequestDispatcher(JspHelper.getPath("add_product")).include(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
//        else {
//            TODO getting product by id
//        }
    }

    @RequestMapping(value = "/product/*", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SellerDto seller = (SellerDto) request.getSession().getAttribute("seller");
        if (seller == null) {
            return;
        }

        String imagePath = ImageUploadHelper.uploadImage(request.getPart("imageProduct"), getServletContext().getRealPath("/"));

        ProductDto productDto = ProductDto.builder().
                name(request.getParameter("name")).
                price(Float.parseFloat(request.getParameter("price"))).
                imagePath(imagePath).
                currency(Currency.getInstance(request.getParameter("currency"))).
                description(request.getParameter("description")).
                type(ProductType.valueOf(request.getParameter("type"))).
                seller(seller).
                build();

        productService.saveProduct(productDto);
        try {
            response.sendRedirect("/profile");
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
