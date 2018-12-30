/*********************************************公共对象***********************************************************/
/*
 * 1.正则表达式
 */
var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//邮箱正则表达式
var telphoneReg=/^[1][3,4,5,7,8][0-9]{9}$/;
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

/**
 * 判定两个时间相差多少年
 * @param startDateStr 起始时间
 * @param endDateStr 结束时间
 * @returns
 */
function getDateYearSub(startDate, endDate) {
    var day = 24 * 60 * 60 *1000; 
    //得到前一天(算头不算尾)
    startDate = new Date(startDate.getTime() - day);
    //获得各自的年、月、日
    var sY  = startDate.getFullYear();     
    var sM  = startDate.getMonth()+1;
    var sD  = startDate.getDate();
    var eY  = endDate.getFullYear();
    var eM  = endDate.getMonth()+1;
    var eD  = endDate.getDate();
    return eY - sY;
}

/*********************************************公共方法***********************************************************/
