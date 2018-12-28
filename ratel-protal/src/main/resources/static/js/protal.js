/* 页面加载时触发事件 */
window.onload=function(){ 
	//页面初始化时，设置高度
	setAsideHeight();
}

window.onresize = function(){
	//页面改变窗口时，设置高度
	setAsideHeight();
}

/**
* 修改页面主体高度
*/
function setAsideHeight(){
	document.getElementById("aside").style.height= (getWinHeight()-(40)) +"px";
	document.getElementById("main").style.height= (getWinHeight()-(40+30)) +"px";
}