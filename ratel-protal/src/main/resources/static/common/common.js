/*********************************************公共对象***********************************************************/
/*
 * 1.正则表达式
 */
var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//邮箱正则表达式
var telphoneReg=/^[1][3,4,5,7,8][0-9]{9}$/;//手机号验证
var planePhoneReg=/^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;;//座机号验证
var onlyENCn=/^[\u4e00-\u9fa5a-z]+$/gi;//只能输入汉字和英文字母
var specialCharactersReg=/^[\w.\-\u4e00-\u9fa5]+$/;//不能包含特殊字符或空格
/*
 * 2.个人资源代码集合
 */
function tidyResourceCodes(resources){
	var resourceCodes=[];
	if(resources && resources.length>0){
		for(var i=0;i<resources.length;i++){
			var resource=resources[i];
			resourceCodes.push(resource.code);
			if(resource.child && resource.child.length>0){
				for(var j=0;j<resource.child.length;j++){
					var childResource=(resource.child)[j];
					resourceCodes.push(childResource.code);
					if(childResource.child && childResource.child.length>0){
						for(var z=0;z<childResource.child.length;z++){
							var btnResource=(childResource.child)[z];
							resourceCodes.push(btnResource.code);
						}
					}
				}
			}
		}
	}
	return resourceCodes;
}
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

/**
 * 获取所有上级父节点node，不包含本级
 * @param treeData 所有树节点数据
 * @param id 本级id
 * @returns
 */
function getAllParentNode(treeData,id){
	var childNode=[];
	if(treeData && treeData.length>0){
		for(var i=0;i<treeData.length;i++){
			var menuNode=treeData[i];
			if(id===menuNode.id){
				return childNode;
			}
			if(menuNode && menuNode.children.length>0){
				for(var j=0;j<menuNode.children.length;j++){
					var pageNode=(menuNode.children)[j];
					if(id===pageNode.id){
						childNode.push(menuNode);
						return childNode;
					}
					if(pageNode && pageNode.children.length>0){
						for(var z=0;z<pageNode.children.length;z++){
							var btnNode=(pageNode.children)[z];
							if(id===btnNode.id){
								childNode.push(pageNode);
								childNode.push(menuNode);
								return childNode;
							}
						}
					}
				}
			}
		}
	}
	return childNode;
}

/**
 * 获取所有子节点node，不包含本级
 * @param treeData 所有树节点数据
 * @param id 本级id
 * @returns
 */
function getAllChildNode(treeData,id){
	var childNode=[];
	if(treeData && treeData.length>0){
		for(var i=0;i<treeData.length;i++){
			var menuNode=treeData[i];
			if(id===menuNode.id){
				if(menuNode && menuNode.children.length>0){
					for(var j=0;j<menuNode.children.length;j++){
						var pageNode=(menuNode.children)[j];
						childNode.push(pageNode);
						if(pageNode && pageNode.children.length>0){
							for(var z=0;z<pageNode.children.length;z++){
								var btnNode=(pageNode.children)[z];
								childNode.push(btnNode);
							}
						}
					}
				}
			}
			if(menuNode && menuNode.children.length>0){
				for(var j=0;j<menuNode.children.length;j++){
					var pageNode=(menuNode.children)[j];
					if(id===pageNode.id){
						if(pageNode && pageNode.children.length>0){
							for(var z=0;z<pageNode.children.length;z++){
								var btnNode=(pageNode.children)[z];
								childNode.push(btnNode);
							}
						}
					}
				}
			}
		}
	}
	return childNode;
}

Array.prototype.remove = function(val) { 
	var index = this.indexOf(val); 
	if (index > -1) { 
		this.splice(index, 1); 
	} 
	return this;
};

Array.prototype.removeTreeNode = function(node) {
	var index=-1;
	for(var i=0;i<this.length;i++){
		if(this[i].id===node.id){
			index=i;
			break;
		}
	}
	if (index > -1) { 
		this.splice(index, 1); 
	} 
	return this;
};

/*********************************************公共方法***********************************************************/
