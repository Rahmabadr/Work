var index;

$( document ).ready(function() {
    changeDate();
    $('.typerequest').change(onTypeChange);
    $('.cell-from').change(onFromChange);
    $('.button-add').click(onAddProduct);
    $('.button-send').click(onSend);
});


var onSend = function (event) {
    event.preventDefault();
    if($('.nameval').eq(0).val() == ""){
        $('.nameval').eq(0).val($('.typerequest').eq(0).val());
    }
    var request = {
        name: $('.nameval').eq(0).val(),
        department: $('.fromdeptype').eq(0).val(),
        type: $('.typerequest').eq(0).val(),
        date: (new Date()).getFullYear() + "-0" + ((new Date()).getMonth() + 1) + "-0" + (new Date()).getDate(),
        deadline: $('.datetype').eq(0).val(),
        officer: $('.officertype').eq(0).val()
    }
    var products = [], jProducts = $('.one-product');
    for (var i = 0; i < jProducts.size(); i++) {
       var quantity = jProducts.eq(i).children(".thisquantity").val();
       if(quantity == ""){
           quantity = 1;
       }
        products.push({
            from: jProducts.eq(i).children(".cell-from").val(),
            to: jProducts.eq(i).children(".cell-to").val(),
            product: jProducts.eq(i).children(".thisproduct").val(),
            quantity: quantity
        });
    }
    
    index = 0;
    addAjaxQuery(
        "request/",
        "post",
        request,
        getAjaxFunction(products),
        function (error) {
            //alert(error);
            console.log(error);
        }
    );
}

var getAjaxFunction = function (products) {
    return function (data) {
        var id = data.requestid;
//        alert(id);
        if(index < products.length ){
            products[index].requestID = id;
            addAjaxQuery(
                "addproduct/",
                "post",
                products[index],
                getAjaxFunction(products),
                function (error) {
                    alert(error);
                    console.log(error);
                }
            );
            index++;
        } else {
            alert("Запрос создан. №" + id + ".");
        }
    }
}


var addAjaxQuery = function(path, method, data, success, error) {
    $.ajax({
      url: path,
      type: method,
      data: data,
      success: success,
      error: error
    });
}



var onAddProduct = function (event) {
    event.preventDefault();
    var elem = $('.one-product').last().clone();
    elem.change(onFromChange);
    elem.insertAfter($('.one-product').last());
    
}

var changeDate = function () {
    $('.thisquantity').eq(0).clone().attr('type','number').attr("min", 1).val(1).insertAfter('.thisquantity').prev().remove();
    $('.datetype').eq(0).clone().attr('type','date').insertAfter('.datetype').prev().remove();
    $('.datetype').eq(0).val((new Date()).getFullYear() + "-0" + ((new Date()).getMonth() + 1) + "-0" + (new Date()).getDate());
    $('.datetype').eq(0).attr('min', (new Date()).getFullYear() + "-0" + ((new Date()).getMonth() + 1) + "-0" + (new Date()).getDate());
}

var onTypeChange = function(){
    var value = $(this).val();
    if(value == "Перемещение") {
        $('.cell-from').attr('disabled', false);
        $('.cell-from').css({'color': "black"});
        
        $('.cell-to').attr('disabled', false);
        $('.cell-to').css({'color': "black"});
        changeOut();
    } else if(value == "Отгрузка"){
        $('.cell-from').attr('disabled', false);
        $('.cell-from').css({'color': "black"});
        
        $('.cell-to').attr('disabled', true);
        $('.cell-to').css({'color': "white"});
        changeOut();
    } else {
        $('.cell-from').attr('disabled', true);
        $('.cell-from').css({'color': "white"});
        
        $('.cell-to').attr('disabled', false);
        $('.cell-to').css({'color': "black"});
        changeIn();
    }
}

var last = '';
var onFromChange = function (event) {
    changeOut();
//    var thisEl = $(this);
//    if(last != '')
//        thisEl.parent().children('.cell-to').append('<option value="' + last + '">' +  last + "</option>");
//    var thisVal = thisEl.children("option:selected").text();
//    var to = thisEl.parent().children('.cell-to').children('option');
//    for (var i = 0; i < to.size(); i++) {
//        if(to.eq(i).text() == thisVal){
//            last = to.eq(i).text();
//            to.eq(i).remove();
//        }
//    }
}

var changeIn = function () {
    addAjaxQuery(
        "getproducts/",
        "post",
        {cellid: "0"},
        onChangedProductAll,
        onChangedProductAll
//        function (error) {
//            alert(error);
//            console.log("09",error);
//        }
    );
}

var id;
var changeOut = function () {
    id = 0;
    var cells = $(".cell-from");
    for (var i = 0; i < cells.size(); i++) {
        addAjaxQuery(
        "getproducts/",
        "post",
        {cellid: $(".cell-from").eq(i).val()},
        returnFun(id),
        returnFun(id)
//        function (error) {
//            alert(error);
//            console.log("0", error);
//        }
        );
        id++;
    }

}

var returnFun = function (id){
    return function (data) {
        var products = data.responseText.split("[")[1].split("]")[0].split(", ");
        console.log(products);

    //    var selects = $(".thisproduct");
        var select = $(".thisproduct").eq(id);
        select.empty();

        for (var i = 0; i < products.length; i++) {
            console.log("@@@",i)
            select.append('<option value="' + products[i] + '">' +  products[i] + "</option>");
        }  
    }
}

//var returnFunChange = function (id) {
//    return
//}

var onChangedProductAll = function (data) {
    var products = data.responseText.split("[")[1].split("]")[0].split(", ");
    console.log(products);
    var selects = $(".thisproduct");
//    alert(selects.size())
    for (var i = 1; i <= selects.size(); i++) {
        console.log(i, "@W@")
        var select = $(".thisproduct");
        select.empty();
        for (var i = 0; i < products.length; i++) {
            var newOption = $('<option value="' + products[i] + '">' +  products[i] + "</option>");
            select.append('<option value="' + products[i] + '">' +  products[i] + "</option>");
        }  
    }

}