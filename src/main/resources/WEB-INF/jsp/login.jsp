<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <jsp:include page="base/head.jsp"/>
  <link rel="stylesheet" href="./css/form.css">

  <title>Log in</title>

  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="../js/login.js"></script>
</head>
<body>
<jsp:include page="base/header.jsp"/>

<div id="body__div" style="margin-top: 300px;">
  <form id="login__form" action="${pageContext.request.contextPath}/login" method="post">
    <c:if test="${param.error != null}">
      <div style="color: red">
        <span>Invalid username || password</span>
      </div>
    </c:if>
    <div class="row mb-3" style="margin-top: 10px">
      <label for="username" class="col-auto col-form-label">Username</label>
      <div class="col-auto">
        <input id="username" name="username" value="${param.username}" type="text" class="form-control"
               placeholder="Username">
      </div>
    </div>
    <div class="row mb-3">
      <label for="password" class="col-auto col-form-label">Password</label>
      <div class="col-auto">
        <input type="password" id="password" class="form-control" name="password" placeholder="Password">
      </div>
    </div>

    <div class="hidden">
      <label for="state"></label>
      <input type="text" id="state" name="state" placeholder="state" value="user">
    </div>

    <div class="row mb-3">
      <button type="submit" class="btn btn-primary">Log in</button>
    </div>

    <div class="row">
      <a id="button__seller_registration" type="submit" class="button__registration">
        I'm a seller
      </a>
      <a id="button__user_registration" type="submit" class="button__registration">
        I'm a user
      </a>
    </div>

  </form>
</div>

<jsp:include page="base/footer.jsp"/>
<jsp:include page="base/script.jsp"/>
</body>
</html>
