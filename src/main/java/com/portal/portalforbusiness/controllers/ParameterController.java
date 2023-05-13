package com.portal.portalforbusiness.controllers;

import com.portal.portalforbusiness.dto.ParameterDto;
import com.portal.portalforbusiness.models.ProductType;
import com.portal.portalforbusiness.services.ParameterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import static com.portal.portalforbusiness.utils.Utils.getEntityIdFromUrl;
import static com.portal.portalforbusiness.utils.Utils.sendParametersJsonResponse;

@Controller
public class ParameterController {

    private final ParameterService parameterService = ParameterService.getInstance();

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getEntityIdFromUrl(request);
        ParameterDto parameter = ParameterDto.builder().
                id(id).
                build();

        if (request.getParameter("soft").equals("True")) {
            parameterService.block(parameter).
                    ifPresentOrElse(userDto -> response.setStatus(204), () -> response.setStatus(404));
        } else {
            parameterService.delete(parameter).
                    ifPresentOrElse(userDto -> response.setStatus(204), () -> response.setStatus(404));
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productType = request.getParameter("productType");

        parameterService.getParametersByProductType(ProductType.valueOf(productType)).ifPresent(productTypeParametersDto ->
                {
                    try {
                        sendParametersJsonResponse(productTypeParametersDto, response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ParameterDto parameterDto = ParameterDto.builder().
                name(request.getParameter("name")).
                productType(ProductType.valueOf(request.getParameter("productType"))).
                build();

        parameterService.save(parameterDto);
    }
}
