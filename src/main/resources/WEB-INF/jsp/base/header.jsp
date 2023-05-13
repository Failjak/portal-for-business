<%@ page import="com.sun.tools.jconsole.JConsoleContext" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
        <div class="container-fluid">
            <button
                    class="navbar-toggler"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#navbarExample01"
                    aria-controls="navbarExample01"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarExample01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item active">
                        <a class="nav-link" aria-current="page" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/products">Products</a>
                    </li>
                    <%--                    <li class="nav-item">--%>
                    <%--                        <a class="nav-link" href="#">Pricing</a>--%>
                    <%--                    </li>--%>
                </ul>
                <%
                    if (
                            request.getSession().getAttribute("user") == null &&
                                    request.getSession().getAttribute("seller") == null
                    ) {
                        out.println("<ul class=\"navbar-nav justify-content-end\">\n" +
                                "                    <li class=\"nav-item\">\n" +
                                "                        <a id=\"navbar__login\" class=\"nav-link nav-btn\" href=\"/login\">Log in</a>\n" +
                                "                    </li>\n" +
                                "                    <li class=\"nav-item\">\n" +
                                "                        <a id=\"navbar__register\" class=\"nav-link nav-btn\" href=\"/register\">Sign up</a>\n" +
                                "                    </li>\n" +
                                "                </ul>");
                    } else {
                        out.println("                <ul class=\"navbar-nav justify-content-end\">\n" +
                                "                    <li class=\"nav-item\">\n" +
                                "                        <a id=\"navbar__profile\" class=\"nav-link nav-btn\" href=\"/profile\">Profile</a>\n" +
                                "                    </li>\n" +
                                "                    <li class=\"nav-item\">\n" +
                                "                        <a id=\"navbar__logout\" class=\"nav-link nav-btn\" href=\"/logout\">Log out</a>\n" +
                                "                    </li>\n" +
                                "                </ul>");
                    }
                %>
            </div>
        </div>
    </nav>
</header>