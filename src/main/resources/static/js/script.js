/**
 *
 *  页面刷新函数
 *
 */

function reloadPage(){
    location.reload()
}

/**
 *
 *  获取本地名为cname的Cookie
 *
 */

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function setparentheight(){

    var bodyHeight = document.body.scrollHeight + 20;

    if (bodyHeight <= 420){
        parent.document.all(self.name).height=420;
    } else {
        parent.document.all(self.name).height=bodyHeight;
    }
}

