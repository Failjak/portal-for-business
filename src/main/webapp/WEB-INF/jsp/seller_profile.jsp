<%@ page import="java.util.List" %>
<%@ page import="dto.SellerDto" %>
<%@ page import="dto.ProductDto" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <jsp:include page="base/head.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <title>Log in</title>
    <link rel="stylesheet" href="./css/form.css">
    <link rel="stylesheet" href="./css/profile.css">

    <script src="../js/seller_actions.js"></script>
    <script src="../js/utils.js" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="base/header.jsp"/>
<%
    SellerDto seller = (SellerDto) request.getSession().getAttribute("seller");
    List<ProductDto> products = (List<ProductDto>) request.getSession().getAttribute("products");
%>
<%!
    public String generateProductsList(List<ProductDto> products) {
        StringBuilder res = new StringBuilder();
        for (ProductDto tmpProduct : products) {
            String btnId = "btn__user_delete_" + tmpProduct.getId();
            res.append("\n" + "<tr>\n" + "    <th scope=\"row\">").append(tmpProduct.getId()).append("</th>\n").append("    <td>").append(tmpProduct.getName()).append("</td>\n").append("    <td>").append(tmpProduct.getPrice()).append("</td>\n").append("    <td>").append(tmpProduct.getCurrency()).append("</td>\n").append("    <td>").append(tmpProduct.getDescription()).append("</td>\n").append("    <td>").append(tmpProduct.getType()).append("</td>\n").append("<td><button class=\"button_to_delete\" id=").append(btnId).append("></button></td>").append("</tr>");
        }
        res.append("<tr><td>...</td></tr>");
        return res.toString();
    }
%>


<div id="body__div">
    <section class="gradient-custom-2">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-lg-9 col-xl-7">
                    <div class="card">
                        <div class="rounded-top text-white d-flex flex-row"
                             style="background-color: #000; height:200px;">
                            <div class="ms-4 mt-5 d-flex flex-column" style="width: 150px;">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-profiles/avatar-1.webp"
                                     alt="Generic placeholder image" class="img-fluid img-thumbnail mt-4 mb-2"
                                     style="width: 150px; z-index: 1">

                                <button type="button" class="btn btn-outline-dark" data-mdb-ripple-color="dark"
                                        style="z-index: 1;">
                                    Edit profile
                                </button>

                            </div>

                            <div class="ms-3" style="margin-top: 130px;">
                                <h5 id="profile__store_name">Store name: <%=seller.getStoreName()%>
                                </h5>
                                <p id="profile__username">Username: <%=seller.getUsername()%>
                                </p>
                            </div>
                        </div>
                        <div class="p-4 text-black" style="background-color: #f8f9fa;">
                            <div class="d-flex justify-content-end text-center py-1">
                                <div>
                                    <p class="mb-1 h5">253</p>
                                    <p class="small text-muted mb-0">Photos</p>
                                </div>
                                <div class="px-3">
                                    <p class="mb-1 h5">1026</p>
                                    <p class="small text-muted mb-0">Followers</p>
                                </div>
                                <div>
                                    <p class="mb-1 h5">478</p>
                                    <p class="small text-muted mb-0">Following</p>
                                </div>
                            </div>
                        </div>
                        <div class="card-body p-4 text-black">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <p class="lead fw-normal mb-0">Products:</p>
                                <a href="/product" type="submit" class="text-muted">Add product</a>
                            </div>
                            <table class="table">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Currency</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Type</th>
                                </tr>
                                </thead>
                                <tbody id="table__tbody">
                                <%=generateProductsList(products)%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<jsp:include page="base/footer.jsp"/>
<jsp:include page="base/script.jsp"/>
</body>
</html>
