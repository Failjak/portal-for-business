<%@ page import="dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.SellerDto" %>
<%@ page import="dto.ParameterDto" %>

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

  <script src="../js/admin_actions.js"></script>
  <script src="../js/utils.js" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="base/header.jsp"/>
<%
  UserDto user = (UserDto) request.getSession().getAttribute("user");
  List<UserDto> users = (List<UserDto>) request.getSession().getAttribute("users");
  List<SellerDto> sellers = (List<SellerDto>) request.getSession().getAttribute("sellers");
  List<ParameterDto> parameters = (List<ParameterDto>) request.getSession().getAttribute("parameters");

%>
<%!
  public String generateUsersList(List<UserDto> users) {
    StringBuilder res = new StringBuilder();
    for (UserDto tmpUser : users) {
      String btnId = "btn__user_delete_" + tmpUser.getId();
      String rowClass = tmpUser.getIsActive() ? "" : "blocked";

      res.append("<tr class='").append(rowClass).append("'><th scope=\"row\">").append(tmpUser.getId()).append("</th>").append("<td>").append(tmpUser.getUsername()).append("</td>").append("<td>").append(tmpUser.getPassword()).append("</td>").append("<td>").append(tmpUser.getRole()).append("</td>").append("<td><button class=\"button_to_delete\" id=").append(btnId).append("></button></td>");
      res.append("<td><button class=\"button_to_block\" id=").append(btnId).append("></button></td>").append("</tr>");
    }
    return res.toString();
  }

  public String generateSellersList(List<SellerDto> sellers) {
    StringBuilder res = new StringBuilder();
    for (SellerDto seller : sellers) {
      String btnId = "btn__seller_delete_" + seller.getId();
      res.append("<tr><th scope=\"row\">").append(seller.getId()).append("</th>").append("<td>").append(seller.getStoreName()).append("</td>").append("<td>").append(seller.getUsername()).append("</td>").append("<td>").append(seller.getPassword()).append("</td>").append("<td><button class=\"button_to_delete\" id=").append(btnId).append("></button></td>").append("</tr>");
//      res.append("<td><button class=\"button_to_block\" id=").append(btnId).append("></button></td>").append("</tr>");
    }
    return res.toString();
  }

  public String generateParametersList(List<ParameterDto> parameters) {
    StringBuilder res = new StringBuilder();
    for (ParameterDto parameter : parameters) {
      String btnId = "btn__parameter_delete_" + parameter.getId();
      String rowClass = parameter.getIsActive() ? "" : "blocked";

      res.append("<tr class='").append(rowClass).append("'><th scope=\"row\">").append(parameter.getId()).append("</th>").append("<td>").append(parameter.getProductType()).append("</td>").append("<td>").append(parameter.getName()).append("</td>").append("<td><button class=\"button_to_delete\" id=").append(btnId).append("></button></td>");
      res.append("<td><button class=\"button_to_block\" id=").append(btnId).append("></button></td>").append("</tr>");
    }
    return res.toString();
  }
%>

<div id="body__div">
  <section class="gradient-custom-2">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col col-lg-9 col-xl-7">
          <div class="rounded-top text-white d-flex flex-row" style="background-color: #000; height:200px;">
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
              <h5 id="profile__username">Username: <%=user.getUsername()%>
                </h5>
                <p id="profile__role">Role: <%=user.getRole()%>
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
                <p class="lead fw-normal mb-0">Users:</p>
              </div>
              <table class="table">
                <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Username</th>
                  <th scope="col">Password</th>
                  <th scope="col">Role</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
                </thead>
                <tbody class="table__tbody">
                <%=generateUsersList(users)%>
                </tbody>
              </table>

              <hr style="height:2px; width:100%; border-width:0; color:black; background-color:black">

              <div class="d-flex justify-content-between align-items-center mb-4">
                <p class="lead fw-normal mb-0">Sellers:</p>
              </div>
              <table class="table">
                <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Store Name</th>
                  <th scope="col">Username</th>
                  <th scope="col">Password</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
                </thead>
                <tbody class="table__tbody">
                <%=generateSellersList(sellers)%>
                </tbody>
              </table>

              <hr style="height:2px; width:100%; border-width:0; color:black; background-color:black">

              <div class="d-flex justify-content-between align-items-center mb-4">
                <p class="lead fw-normal mb-0">Parameters:</p>
              </div>
                <table class="table">
                  <thead class="thead-dark">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Product Type</th>
                    <th scope="col">Name</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                  </tr>
                  </thead>
                  <tbody class="table__tbody">
                  <%=generateParametersList(parameters)%>
                  </tbody>
                </table>
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
