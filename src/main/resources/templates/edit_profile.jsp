<%@ page import="dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="base/head.jsp"/>
    <title>Editing Profile</title>
    <link rel="stylesheet" href="./css/form.css">
    <link rel="stylesheet" href="./css/profile.css">

    <script src="../js/profile.js"></script>

</head>
<%
    UserDto user = (UserDto) request.getSession().getAttribute("user");
%>

<body>
<jsp:include page="base/header.jsp"/>
<div id="body__div">
    <section class="gradient-custom-2">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-lg-9 col-xl-7">
                    <div class="card">
                        <div class="rounded-top text-white d-flex flex-row"
                             style="background-color: #000; height:220px;">
                            <div id="edit__div-avatar">
                                <img id="edit__avatar" src="../static/images/avatar.png" alt="Generic placeholder image"
                                     class="img-fluid img-thumbnail mt-4 mb-2">
                            </div>
                        </div>

                        <form id="profile__form" action="${pageContext.request.contextPath}/user/" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="_method" value="put">

                            <div class="row mb-3">
                                <label for="edit__username" class="col-auto col-form-label">Username</label>
                                <div class="col-auto">
                                    <input type="text" id="edit__username" class="form-control" name="username"
                                           placeholder="Username" value="<%=user.getUsername()%>">
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label for="edit__password" class="col-auto col-form-label">Password</label>
                                <div class="col-auto">
                                    <input type="password" id="edit__password" class="form-control"
                                           name="password" placeholder="Password" value="<%=user.getPassword()%>">
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label for="edit__photo" class="col-auto col-form-label">Photo</label>
                                <div class="col-auto">
                                    <input type="file" id="edit__photo" class="form-control" name="photo"
                                           value="" accept="image/*"
                                           alt="Profile photo">
                                </div>
                            </div>
                            <button id='edit_profile__send' type='submit' class='btn btn-primary'>Update</button>
                        </form>

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
