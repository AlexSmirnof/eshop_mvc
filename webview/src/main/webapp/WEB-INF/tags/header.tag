<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="showCartWidget" type="java.lang.Boolean" %>
<c:set var="productList" value="${pageContext.request.contextPath}/productList" scope="page"/>

<style>
    #icon{
        color: #000;
        text-decoration: none;
    }
</style>


<header>
    <div class="phone">
        <a id="icon" href="${productList}">
            <span class="glyphicon glyphicon-phone"></span>
        </a>
        Phonify
    </div> <%--Gallivant--%>
        <c:if test="${showCartWidget}">
            <button id="cartWidget" class="btn btn-info"><u>My cart:</u>
            <span id="cartItems">${empty cartForm.totalQuantity ? 0 : cartForm.totalQuantity}</span>
            items <span id="cartPrice">${empty cartForm.totalPrice ? 0.00 : cartForm.totalPrice}</span>$
            </button>
        </c:if>

    <div class="clear"></div>
</header>