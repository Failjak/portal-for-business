<%@ page import="dto.SellerDto" %>
<%@ page import="java.util.List" %>
<%
    List<SellerDto> sellers = (List<SellerDto>) request.getSession().getAttribute("sellers");
%>

<%!
    public String generateBlockForCarousel(List<SellerDto> sellers) {
        String fonImageForSeller = "../../static/images/background1.jpg";
        StringBuilder res = new StringBuilder();
        boolean flag = true;
        for (SellerDto seller : sellers) {
            String sellerUrl = "seller/" + seller.getId();
            if (flag) {
                res.append("<div class=\"carousel-item active\">");
                flag = false;
            } else {
                res.append("<div class=\"carousel-item\">");
            }
            res.append("<a href=").append(sellerUrl).append("><img src=").append(fonImageForSeller).append(" class=\"d-block w-100 carousel-block\" alt=").append(seller.getId()).append("></a><div class=\"carousel-caption d-none d-md-block\"><h5>").append(seller.getStoreName()).append("</h5><p>Must be some statistics</p></div></div>");
        }
        return res.toString();
    }

    public String generateCarouselIndicators(Integer len) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            res.append("<button type='button' data-bs-target='#best-sellers' data-bs-slide-to='").append(i).append("' aria-label='Slide ").append(i).append("'");
            if (i == 0) {
                res.append("aria-current='true' class='active'");
            }
            res.append("></button>");
        }
        return res.toString();
    }
%>

<div id="best-sellers" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <%=generateCarouselIndicators(sellers.size())%>
    </div>
    <div class="carousel-inner">
        <%=generateBlockForCarousel(sellers)%>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#best-sellers" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#best-sellers" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
    </button>
</div>