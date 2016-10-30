(function(){
    $(document).ready(function(){
        console.log("ready");
        
        $('#cartBtn').bind('click', function(){
            console.log(location.href);
            location.href = 'cartPage';
            console.log(location.href);
        });
        
        $('.addToCartBtn').each(function () {
            console.log('find addBtn with key=' + this.getAttribute('key'));
            var btn = this;
            var command = 'add';
            btn.onclick = function () {
                var key = this.getAttribute("key");
                console.log('--------------------------------');
                console.log('click on addBtn with key=' + key);
                send(key, command);
            };
        });
        $('.deleteFromCartBtn').each(function () {
            console.log('find delBtn with key=' + this.getAttribute('key'));
            var btn = this;
            var command = 'delete';
            btn.onclick = function () {
                var key = this.getAttribute("key");
                console.log('--------------------------------');
                console.log('click on delBtn with key=' + key);
                send(key, command);
            }
        });
    });
    function send(key, command){
        console.log('send ' + command);
        var path;
        var selector;
        var quantity;
        var data;
        if( command === 'add'){
            path = 'productList/addProductToCart/'.concat(key);
            selector = '.quantityField[key='.concat(key).concat(']');
            quantity = $(selector).val();
            // if( quantity <= 0 ) return;
            data = 'quantity='.concat(quantity);
        } else if( command === 'delete'){
            path = 'cartPage/deleteProductFromCart/'.concat(key);
            selector = '.quantityField[key='.concat(key).concat(']');
            quantity = $(selector).val();
            // if( quantity <= 0 ) return;
            data = 'quantity='.concat(quantity);
        }
        console.log('path: ' + path);
        console.log('data: ' + data);
        $.ajax({
            type: 'post',
            url: path,
            data: data,
            success: updateCartWidget,
            error: showError
        });
    };
    function updateCartWidget(data) {
        console.log(data);
        if (data.indexOf('Error:') === 0) return;
        $.get(
            'cartPage/getCartJson',
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
    function showError(data,error,status){
        console.log(error);
        alert("Data: " + data + "\nStatus: " + status);
//      $('quantity').html("<i style=\"color:red\">An error occured</i>");
    };
})();


