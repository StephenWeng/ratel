/*********************************************公共对象***********************************************************/
/*
 * 1.正则表达式
 */
var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//邮箱正则表达式

/*********************************************公共对象***********************************************************/

/*********************************************公共方法***********************************************************/
/**
 * 获取当前屏幕高度
 * @returns 屏幕高度，不包含px
 */
function getWinHeight(){
	var winHeight=0;
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;
    //通过Document对body进行检测，获取浏览器可视化高度
    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.clientHeight;
    return winHeight;
}


/**
 * 休眠N秒
 * @param numberMillis 需要休眠的毫秒数
 * @returns
 */
function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime)
		return;
	}
}
/*********************************************公共方法***********************************************************/
