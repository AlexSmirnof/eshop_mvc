<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="productList" value="${pageContext.request.contextPath}/productList" scope="page"/>
<c:set var="cartPage" value="${pageContext.request.contextPath}/cart" scope="page"/>
<c:set var="orderPage" value="${pageContext.request.contextPath}/order" scope="page"/>

<html>
<head>
    <title>Order</title>
    <tag:style/>
    <tag:script/>

</head>
<body>
<div class="container">
    <div class="row">
        <tag:header/>
    </div>
    <div class="row">
        <h2>Order</h2>
    </div>
    <div class="row">
        <nav class="cart">
            <a href="${cartPage}"><button class="back">Back to Cart</button></a>
        </nav>
    </div>
    <div class="row">
        <table class="product cart">
            <tr>
                <th>Model</th>
                <th>Color</th>
                <th>Display size</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <c:forEach var="orderItem" items="${order.orderItems}">
                <c:set var="phone" value="${orderItem.phone}" scope="page"/>
                <tr>
                    <td>${phone.model}</td>
                    <td>${phone.color}</td>
                    <td>${phone.displaySize}</td>
                    <td>${orderItem.quantity}</td>
                    <td>${phone.price * orderItem.quantity}$</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>Subtotal</td>
                <td>${order.totalPrice}$</td>
            </tr>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>Delivery</td>
                <td>5$</td>
            </tr>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>TOTAL</td>
                <td>
                    <%--<fmt:formatNumber var="total" pattern="000.00" value="${orderC.totalPrice + 5}"/>--%>
                    <%--<c:out value="${total}"/>--%>
                    ${order.totalPrice + 5}$
                </td>
            </tr>
        </table>
    </div>

    <div class="row">
        <form:form action="${orderPage}/confirm" method="POST" modelAttribute="order">
        <table class="order">
            <tr>
                <td>First name</td>
                <td>
                    <form:input path="firstName"  placeholder="First name" />
                </td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>
                    <form:input path="lastName"  placeholder="Last name" />
                </td>
            </tr>
            <tr>
                <td>Address</td>
                <td>
                    <form:input path="deliveryAddress"  placeholder="Address" />
                </td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>
                    <form:input path="contactPhoneNo"  placeholder="Phone" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <form:textarea path="description"  placeholder="Additional information" ></form:textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <form:button>Order</form:button>
                </td>
            </tr>
        </table>
        </form:form>
    </div>
</div>
</body>
</html>
