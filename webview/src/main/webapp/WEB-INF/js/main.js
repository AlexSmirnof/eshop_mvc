(function(window){

    var path;
    var selector;
    var quantity;
    var data;
    
    function doPost( path, data, onSuccess, onError ){
        $.ajax({
            type: 'post',
            url: path,
            data: data,
            success: onSuccess,
            error: onError
        });
    };
    
    function showError(data,error,status){
        console.log(error);
        alert("Data: " + data + "\nStatus: " + status);
//      $('quantity').html("<i style=\"color:red\">An error occured</i>");
    };
    
    function updateCartWidget(data) {
        console.log(data);
        if (data.indexOf('Error:') === 0) return;
        $.get(
            'cart/getCartJson',
            {},
            function (data) {
                console.log('getJson');
                console.log(data);
                $('#cartItems').html(data.quantity);
                $('#cartPrice').html(parseFloat(data.price).toFixed(2));
            },
            'json'
        );
    };
    
    function sendAdd(key){
        console.log('sendAdd');
        path = 'cart/addProductToCart/'.concat(key);
        selector = '.quantityField[key='.concat(key).concat(']');
        quantity = $(selector).val();
        // if( quantity <= 0 ) return;
        data = 'quantity='.concat(quantity);
        console.log('path: ' + path);
        console.log('data: ' + data);
        doPost( path, data, updateCartWidget, showError );

    };

    function sendDelete(key){
        console.log('sendDelete');
        path = 'cart/deleteProductFromCart/'.concat(key);
        selector = '.quantityField[key='.concat(key).concat(']');
        quantity = $(selector).val();
        // if( quantity <= 0 ) return;
        data = 'quantity='.concat(quantity);
        console.log('path: ' + path);
        console.log('data: ' + data);
        doPost( path, data, updateCartWidget, showError );
    };
    
    window.AddToCart = function () {
        console.log("addToCart ready");
        $('.addToCartBtn').each(function () {
            console.log('find addBtn with key=' + this.getAttribute('key'));
            var btn = this;
            btn.onclick = function () {
                var key = this.getAttribute("key");
                console.log('--------------------------------');
                console.log('click on addBtn with key=' + key);
                sendAdd(key);
            };
        });
    };

    window.DeleteFromCart = function () {
        console.log("deleteFromCart ready");
        $('.deleteFromCartBtn').each(function () {
            console.log('find delBtn with key=' + this.getAttribute('key'));
            var btn = this;
            btn.onclick = function () {
                var key = this.getAttribute("key");
                console.log('--------------------------------');
                console.log('click on delBtn with key=' + key);
                sendDelete(key);
            }
        });
    };
    
    /*переход на cart.jsp при клике на cartWidget*/
    $(document).ready(function() {
        $('#cartBtn').bind('click', function () {
            location.href = 'cart';
        });
    });

})(window);


