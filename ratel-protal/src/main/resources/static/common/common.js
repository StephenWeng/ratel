/*********************************************公共对象***********************************************************/
/*
 * 1.正则表达式
 */
var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//邮箱正则表达式
var telphoneReg=/^[1][3,4,5,7,8][0-9]{9}$/;//手机号验证
var planePhoneReg=/^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;;//座机号验证
var onlyENCn=/^[\u4e00-\u9fa5a-z]+$/gi;//只能输入汉字和英文字母
var specialCharactersReg=/^[\w.\-\u4e00-\u9fa5]+$/;//不能包含特殊字符或空格
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

/**
 * 树节点增加下级节点
 * @param node 当前节点
 * @param data 下级数据
 * @returns
 */
function addNode_tree(node,data){
	var treeNode={};
	treeNode.id=data.id;
	treeNode.label=data.name;
	treeNode.children=[];
	node.children.push(treeNode);
}

/**
 * 树节点修改下级节点名称
 * @param node
 * @param data
 * @returns
 */
function updateNode_tree(node,data){
	for(var i in node.children){
		var child=(node.children)[i];
		if(child.id==data.id){
			child.label=data.name;
			break;
		}
	}
}

/**
 * 树节点删除多个下级节点
 * @param node
 * @param ids
 * @returns
 */
function delNode_tree(node,ids){
	for(var i in ids){
		var id=ids[i];
		for(var j in node.children){
			var children=(node.children)[j];
			if(children.id==id){
				node.children.splice(j, 1); 
			}
		}
	}
}

/*********************************************公共方法***********************************************************/
