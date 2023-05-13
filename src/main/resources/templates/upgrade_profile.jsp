<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Title</title>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <jsp:include page="base/head.jsp"/>
  <title>Upgrading Profile</title>
  <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="./css/upgrade_profile.css">

</head>

<body>
<jsp:include page="base/header.jsp"/>
<div id="body__div">
  <section class="gradient-custom-2">
    <form class="credit-card" action="${pageContext.request.contextPath}/card/" method="post">
      <div class="form-header">
        <h4 class="title">Credit card detail</h4>
      </div>

      <div class="form-body">
        <input name="card__ccn" class="card-number" type="tel" inputmode="numeric" pattern="[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}" minlength="19" maxlength="19" placeholder="xxxx xxxx xxxx xxxx">
        <span class="validity"></span>

        <div class="date-field">
          <div class="month">
            <select name="card__month">
              <option value="january">January</option>
              <option value="february">February</option>
              <option value="march">March</option>
              <option value="april">April</option>
              <option value="may">May</option>
              <option value="june">June</option>
              <option value="july">July</option>
              <option value="august">August</option>
              <option value="september">September</option>
              <option value="october">October</option>
              <option value="november">November</option>
              <option value="december">December</option>
            </select>
          </div>
          <div class="year">
            <select name="card__year">
              <option value="2023">2023</option>
              <option value="2024">2024</option>
              <option value="2024">2025</option>
              <option value="2024">2026</option>
            </select>
          </div>
        </div>

        <div class="card-verification">
          <div class="cvv-input">
            <input type="text" name="card__cvv" placeholder="CVV" pattern="[0-9]{3}">
          </div>
          <div class="cvv-details">
            <p>3 or 4 digits usually found <br> on the signature strip</p>
          </div>
        </div>

        <button type="submit" class="proceed-btn">Proceed</button>
      </div>
    </form>
  </section>
</div>

<jsp:include page="base/footer.jsp"/>
<jsp:include page="base/script.jsp"/>
</body>
</html>
