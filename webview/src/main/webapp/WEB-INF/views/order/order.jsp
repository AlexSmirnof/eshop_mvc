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
        <c:choose>
            <c:when test="${param.confirm}">
                <h2>Thank you for your submission</h2>
            </c:when>
            <c:otherwise>
                <h2>Order</h2>
            </c:otherwise>
        </c:choose>

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

    <%--confirm: ${param.confirm}<br/>--%>
    <%--class: ${order.getClass()}<br/>--%>
    <%--bean:    ${order}<br/>--%>
    <%--beanC:    ${orderC}<br/>--%>

    <c:if test="${param.confirm}">
        <%--beanName - создаст новый bean--%>
        <%--class - сделает уже сущ-щий бин доступным в указ области--%>
        <jsp:useBean id="order" class="ex.soft.domain.model.Order" scope="request" />
        <jsp:setProperty name="order" property="*"/>
    </c:if>

    <%--after:    ${orderC}<br/>--%>

    <div class="row">
        <form action="${orderPage}" method="POST">
        <table class="order">
            <tr>
                <td>First name</td>
                <td>
                    <c:choose>
                        <c:when test="${param.confirm}">
                            <jsp:getProperty name="order" property="firstName"/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="firstName" placeholder="First name" required>
                        </c:otherwise>
                   </c:choose>
                </td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>
                    <c:choose>
                        <c:when test="${param.confirm}">
                            <jsp:getProperty name="order" property="lastName"/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="lastName" placeholder="Last name" required>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>Address</td>
                <td>
                    <c:choose>
                        <c:when test="${param.confirm}">
                            <jsp:getProperty name="order" property="deliveryAddress"/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="deliveryAddress" placeholder="Address" required>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>
                    <c:choose>
                        <c:when test="${param.confirm}">
                            <jsp:getProperty name="order" property="contactPhoneNo"/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="contactPhoneNo" placeholder="Phone" required>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${param.confirm}">
                            <%--<jsp:getProperty name="orderC" property="description"/>--%>
                            desc: ${param.description}<br>
                            quan:  <jsp:getProperty name="order" property="totalQuantity"/><br/>
                            price:  <jsp:getProperty name="order" property="totalPrice"/><br/>
                            items:  <jsp:getProperty name="order" property="orderItems"/>
                        </c:when>
                        <c:otherwise>
                            <textarea name="description" placeholder="Additional information"></textarea>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                <c:choose>
                    <c:when test="${param.confirm}">
                        <button formaction="${productList}" formmethod="GET">Back To Shopping</button>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="confirm" value="true"/>
                        <button>Order</button>
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
</body>
</html>
