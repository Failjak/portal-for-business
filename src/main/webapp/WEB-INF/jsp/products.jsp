<%@ page import="java.util.List" %>
<%@ page import="dto.ProductDto" %>
<%@ page import="dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="base/head.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Products</title>
    <link rel="stylesheet" href="./css/products.css">

    <script src="../js/products.js"></script>
    <script src="../js/alert.js"></script>
</head>
<body>
<jsp:include page="base/header.jsp"/>
<%
    List<ProductDto> products = (List<ProductDto>) request.getSession().getAttribute("products");
    UserDto userDto = (UserDto) request.getSession().getAttribute("user");
%>

<%!
    public String generateTableProductRows(List<ProductDto> products, UserDto user) {
        StringBuilder res = new StringBuilder();

        List<Integer> boughtProductIds = null;
        if (user != null)
            boughtProductIds = user.getBoughtProductIds();

        for (ProductDto product : products) {
            String productImage = product.getImagePath();
            String productJson = product.getJson();
            String productAvgMark = product.getAvgMark() != null ? String.format("%.1f" +
                    "", product.getAvgMark()) : "No";
            Boolean isBought = boughtProductIds != null && boughtProductIds.contains(product.getId()) ? Boolean.TRUE : Boolean.FALSE;

            res.append("<tr><td><div class='product-cart d-flex'><div class='product-thumb'><img src=").append(productImage).append(" alt='Product'></div><div class='product-content media-body'>").
                    append("<h5 class='title'><a href='#' onclick='showProductModal(event, ").append(productJson).append(",").append(isBought).append(")'>").append(product.getName()).append("</a></h5><span>").append(product.getDescription()).
                    append("</span></div></div></td>").append("<td><span class='avg_mark'>").append(productAvgMark).append("</span></td>").append("<td class='product-cart__button'>");

            if (isBought)
                res.append("<button class='btn btn-primary' style='background-color: green; border-color: green;' disabled>").append("Already bought</button>");
            else
                res.append("<button class='products__buy btn btn-primary' value='").append(product.getId()).append("'>Buy</button>");
            res.append("</td></tr>");
        }
        return res.toString();
    }
%>

<div id="body__div">
    <jsp:include page="base/alert.jsp"/>
    <section class="gradient-custom-2">
        <div class="container py-5 h-100">
            <div id="container__row_top">
                <h1 id="container_row__title">Products</h1>
                <div id="container_row__search">
                    <div class="col-md-5 mx-auto">
                        <form id="search-form" class="input-group" action="${pageContext.request.contextPath}/products/" method="post">
                            <label for="container_row__search__input"></label>
                            <input class="form-control border-end-0 border rounded-pill"
                                   type="search" name="name" id="container_row__search__input">
                    <button id="btn_search" class="btn btn-outline-secondary bg-white border-bottom-0 border rounded-pill ms-n5"
                            type="submit"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                </div>
            </div>
            <div id="carousel__best-sellers">
                <jsp:include page="utils/slides.jsp"/>
            </div>
            <div class="row d-flex justify-content-center mt-50">
                <div class="checkout-style-1">
                    <div class="checkout-table table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Product</th>
                                <th>Rating</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="products__tbody">
                            <%=generateTableProductRows(products, userDto)%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="product_modal.jsp"/>
</div>


<jsp:include page="base/footer.jsp"/>
<jsp:include page="base/script.jsp"/>
</body>
</html>