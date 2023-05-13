<%@ page import="dto.UserDto" %>
<%@ page import="models.types.RoleType" %>
<%@ page import="dto.ProductDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="base/head.jsp"/>
    <title>Log in</title>

    <link rel="stylesheet" href="./css/form.css">
    <link rel="stylesheet" href="./css/profile.css">
    <script src="../js/profile.js"></script>
</head>
<body>

<jsp:include page="base/header.jsp"/>

<%
    UserDto user = (UserDto) request.getSession().getAttribute("user");
    List<ProductDto> boughtProducts = (List<ProductDto>) request.getSession().getAttribute("boughtProducts");
    String imagePath = user.getImagePath() != null ? user.getImagePath() : "../static/images/avatar.png";
%>

<%!
    public String generateBoughtProductsList(List<ProductDto> products) {
        if (products == null) {
            return "Вы ничего не купили ;(";
        }

        StringBuilder res = new StringBuilder();
        for (ProductDto tmpProduct : products) {
            String btnId = "btn__user_delete_" + tmpProduct.getId();
            res.append("<tr><th scope='row'>").append(tmpProduct.getId()).append("</th><td>").append(tmpProduct.getName()).append("</td><td>").append(tmpProduct.getPrice()).append("</td><td>").append(tmpProduct.getCurrency()).append("</td><td>").append(tmpProduct.getDescription()).append("</td><td>").append(tmpProduct.getType()).append("</td></tr>");
        }
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
                            <div class="ms-4 d-flex flex-column" style="width: 150px;">
                                <img src="<%=imagePath%>"
                                     alt="Profile avatar" class="img-fluid img-thumbnail mt-4 mb-2"
                                     style="min-height: 200px; width: 150px; z-index: 1">
                            </div>
                            <div class="ms-3" style="margin-top: 100px;">
                                <h5 style="color: red;"><%=user.getIsActive() ? "" : "Вы заблокированы"%>
                                </h5>
                                <h5 id="profile__username">Username: <%=user.getUsername()%>
                                </h5>
                                <%
                                    if (user.getRole() == RoleType.BUYER) {
                                        out.println("<h6 id='profile__balance'>Balance: " + user.getCard().getBalance() + " BYN</h6>");
                                    }
                                %>
                                <p id="profile__role">Role: <%=user.getRole()%>
                                </p>
                            </div>
                        </div>
                        <div class="p-4 text-black d-flex" style="background-color: #f8f9fa;">
                            <form class="profile__buttons" action="${pageContext.request.contextPath}/user"
                                  method="get">
                                <input type="hidden" id="profile__form__input_for_type" name="change" value="true">
                                <button id="profile__edit" type="submit" class="btn btn-outline-dark"
                                        data-mdb-ripple-color="dark">
                                    Edit profile
                                </button>
                                <%
                                    if (user.getRole() == RoleType.USER) {
                                        out.println("<button id='profile__upgrade' type='submit' class='btn btn-outline-dark'data-mdb-ripple-color=dark'>Upgrade profile</button>");
                                    }
                                %>
                            </form>
                            <div class="profile__additional_info">
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
                                <h1 class="lead fw-normal mb-0">Your purchased products</h1>
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
                                <tbody class="table__tbody">
                                <%=generateBoughtProductsList(boughtProducts)%>
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
