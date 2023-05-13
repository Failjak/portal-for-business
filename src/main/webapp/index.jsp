<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="WEB-INF/jsp/base/head.jsp"/>
</head>
<body>
<jsp:include page="WEB-INF/jsp/base/header.jsp"/>

<div class="container text-center">
    <h1></h1>
    <%--    <form method="get" name="base">--%>
    <%--        <input type="button" name="do_register" class="btn btn-outline-secondary "--%>
    <%--               onclick="{document.base.action='<%= request.getContextPath() %>/register'; document.base.submit();}"--%>
    <%--               value="Sign Up">--%>
    <%--        <input type="button" name="do_login" class="btn btn-outline-secondary"--%>
    <%--               onclick="{document.base.action='<%= request.getContextPath() %>/login'; document.base.submit();}"--%>
    <%--               value="Sign In">--%>
    <%--    </form>--%>
</div>

<jsp:include page="WEB-INF/jsp/base/footer.jsp"/>
</body>
</html>
