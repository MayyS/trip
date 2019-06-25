//获取url上的请求参数
function getParams() {
    //获取问号及问号后面的内容
    var url = window.location.search;
    var params = new Object();
    if (url.indexOf("?") != -1) {
        //截取问号后面的内容,再使用&分割多个属性
        var arr = url.substr(1).split("&");
        for (var i = 0; i < arr.length; i++) {
            //使用=分割为keyvalue
            var keyValue = arr[i].split("=");
            params[keyValue[0]] = keyValue[1];
        }
    }
    return params;
}


//跳转到a链接中的地址
function skipHref() {
    var url = $(this).data("href");
    var id = $(this).data("id");
    //获取id值
    if(!url){
        var a = $(this).find('a');
        url = a.data("href");
        id = a.data("id");
    }
    window.location.href = url + "?id=" + id;
}
