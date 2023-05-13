<%@ page import="models.types.ProductType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <jsp:include page="base/head.jsp"/>
  <link rel="stylesheet" href="./css/product.css">

  <title>Product</title>

  <script src="../js/product.js"></script>

</head>
<body onload="generateProductForm()">
<jsp:include page="base/header.jsp"/>

<div id="body__div">
  <form id="product__form" action="${pageContext.request.contextPath}/product" method="post" enctype="multipart/form-data">
    <div class="row mb-3">
      <label for="name" class="col-auto col-form-label">Product Name</label>
      <div class="col-auto">
        <input type="text" id="name" class="form-control" name="name" placeholder="Product Name">
      </div>
    </div>

    <div class="row mb-3">
      <label for="price" class="col-auto col-form-label">Price</label>
      <div class="col-auto">
        <input type="text" id="price" class="form-control" name="price" pattern="[0-9]+[.]?[0-9]+">
      </div>
      <label for="currency" class="col-auto col-form-label">Currency</label>
      <div class="col-auto">
        <select name="currency" id="currency" class="form-control">
          <option value="USD" selected>USD</option>
          <option value="BYN">BYN</option>
        </select>
      </div>
    </div>

    <div class="row mb-3">
      <label for="description" class="col-auto col-form-label">Description</label>
      <div class="col-auto">
        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
      </div>
    </div>

    <div class="row mb-3">
      <label for="imageProduct" class="col-auto col-form-label">Image</label>
      <div class="col-auto">
        <input type="file" id="imageProduct" class="form-control" name="imageProduct" value="" accept="image/*"
               alt="Products Image">
      </div>
    </div>

    <div class="row mb-3">
      <label for="type" class="col-auto col-form-label">Product Type</label>
      <div class="col-auto">
        <select name="type" id="type" class="form-control">
          <%
            boolean flag = true;
            for (ProductType s : ProductType.values()) {
              if (flag) {
                out.println("<option value=\"" + s + "\" selected>" + s.getDisplayName() + "</option>");
                flag = false;
              }
              else
                out.println("<option value=" + s + " >" + s.getDisplayName() + "</option>");
            }
          %>
        </select>
      </div>
    </div>

    <div class="row mb-3">
      <button type="submit" id="button__create_product" class="btn btn-primary">Create</button>
    </div>

  </form>
</div>

<jsp:include page="base/footer.jsp"/>
<jsp:include page="base/script.jsp"/>
</body>
</html>
