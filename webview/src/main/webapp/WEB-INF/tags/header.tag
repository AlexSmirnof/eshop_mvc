<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="showCartWidget" type="java.lang.Boolean" %>

<header>
    <div class="phone"><span class="glyphicon glyphicon-phone"></span> Phonify</div> <%--Gallivant--%>
        <c:if test="${showCartWidget}">
            <button id="cartWidget"><u>My cart:</u>
            <span id="cartItems">${empty cart.totalQuantity ? 0 : cart.totalQuantity}</span>
            items <span id="cartPrice">${empty cart.totalPrice ? 0 : cart.totalPrice}</span>$
        </c:if>
    </button>
    <div class="clear"></div>
</header>