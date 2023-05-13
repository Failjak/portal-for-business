<%@ page import="dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    UserDto user = (UserDto) request.getSession().getAttribute("user");
%>


<div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="rounded-top text-white d-flex flex-row"
                     style="background-color: #000; height:250px;">
                    <div class="ms-4 d-flex flex-column" style="width: 150px;">
                        <img id="modal__product_image" src="" alt="" class="img-fluid img-thumbnail mt-4 mb-2">
                    </div>
                    <div class="ms-3" style="margin-top: 30px;">
                        <h5 id="modal__product_name" style="color: white;"></h5>
                        <p id="modal__product_description"></p>
                    </div>

                    <div class="ms-3" style="margin-top: 30px;">
                        <p id="modal__product_price" style="color: white;"></p>
                    </div>
                </div>
                <div id="modal__comments"></div>
                <%
                    if (user != null && user.getIsActive().equals(Boolean.TRUE)) {
                        out.println("<form id='modal__form' action='/comment' method='POST'>" +
                                "<div class='mb-3'>" +
                                "<h1>Product evaluation</h1>" +
                                "<input type='text' id='product_evaluation__radios' name='evaluations' hidden>" +
                                "<div id='product_evaluation__content'></div>" +
                                "</div>" +
                                "<div class='mb-3'>" +
                                "<label for='modal__product__message' class='col-form-label'>Comment:</label>" +
                                "<textarea class='form-control' name='comment' id='modal__product__message'></textarea>" +
                                "</div>" +
                                "<div class='modal-footer'>" +
                                "<button id='modal__send' type='submit' class='btn btn-primary'>Send feedback</button>" +
                                "</div>" +
                                "</form>");
                    }
                %>
            </div>

        </div>
    </div>
</div>