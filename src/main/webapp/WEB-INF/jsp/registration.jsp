<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="./css/form.css">

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="../js/registration.js"></script>

    <jsp:include page="base/head.jsp"/>
</head>
<body onload="generateUserRegistrationForm()">
<jsp:include page="base/header.jsp"/>

<div class="container">
    <div class="row justify-content-md-center">
        <div class="col col-lg-2">
            1 of 3
        </div>
        <div id="body__form" class="col-md-auto">
            <form id="registration__form" action="${pageContext.request.contextPath}/register" method="post">
                <div id="registration__form__content"></div>
                <div class="hidden">
                    <label for="state"></label>
                    <input type="text" id="state" name="state" value="user">
                </div>
                <div class="row mb-222">
                    <button id="registration__form__submit" type="submit" class="btn btn-primary">Sign up</button>
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
    </div>
</div>

<jsp:include page="base/footer.jsp"/>
<jsp:include page="base/script.jsp"/>
</body>
</html>
