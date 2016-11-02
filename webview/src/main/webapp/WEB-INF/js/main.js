(function(window){

    var path;
    var data;
    var selector;
    var inputField;
    var quantity;
    
    function doPost( path, data, onSuccess, onError ){
        $.ajax({
            type: 'post',
            url: path,
            data: data,
            success: onSuccess,
            error: onError
        });
    };
    
    function showError(data, status){
        console.log('showError');
        inputField.addClass('errorBorder');
        if(!data.responseText){
            inputField.next().html('<br/>' + data.replace("Error: ",""));
        } else {
            if(data.status == 400){
                inputField.next().html('<br/>' + 'Incorrect number format input');
            } else if(data.status == 500){
                var start = data.responseText.indexOf('Exception:');
                var end = data.responseText.indexOf('.', start);
                var errorMessage = data.responseText.substring(start + 11, end);
                inputField.next().html('<br/>' + errorMessage);
            }
        }
    };

    function clearErrors() {
        console.log('clearErrors');
        $('.quantityField').filter('.errorBorder').each(function () {
            $(this).removeClass('errorBorder');
            $(this).next().html("");
        });
    };

    function updateCartWidget(data) {
        console.log(data);
        if (data.indexOf('Error:') === 0) {
            showError(data);
            return;
        }
        clearErrors();
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
        inputField = $(selector);
        quantity = inputField.val();
        data = 'quantity='.concat(quantity);
        console.log('path: ' + path);
        console.log('data: ' + data);
        doPost( path, data, updateCartWidget, showError );

    };

    function sendDelete(key){
        console.log('sendDelete');
        path = 'cart/deleteProductFromCart/'.concat(key);
        selector = '.quantityField[key='.concat(key).concat(']');
        inputField = $(selector);
        quantity = inputField.val();
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


