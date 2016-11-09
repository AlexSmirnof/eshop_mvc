<%@ tag pageEncoding="UTF-8"%>

<header>
    <div class="phone"><span class="glyphicon glyphicon-phone"></span> Phonify</div> <%--Gallivant--%>
    <button id="cartWidget"><u>My cart:</u>
        <span id="cartItems">${empty cart.totalQuantity ? 0 : cart.totalQuantity}</span>
        items <span id="cartPrice">${empty cart.totalPrice ? 0 : cart.totalPrice}</span>$
    </button>
    <div class="clear"></div>
</header>