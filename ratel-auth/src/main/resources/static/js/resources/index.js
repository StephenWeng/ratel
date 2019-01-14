function createRoleRelationEcharts(id,treeData){
	var myChart = echarts.init(document.getElementById(id));
	option = {
	        backgroundColor: '#0B112B',
	        title: {
	            text: '资源关系图'
	        },
	        tooltip: {},
	        animationDurationUpdate: 1500,
	        animationEasingUpdate: 'quinticInOut',
	        color:['#83e0ff','#45f5ce','#b158ff'],
	        legend: {
	            show: false,
	            data: [
	                {name: '菜单',textStyle:{color:'#fff'} },
	                {name: '页面',textStyle:{color:'#fff'}},
	                {name: '按钮',textStyle:{color:'#fff'}}
	            ]
	        },
	        series: [
	            {
	                type: 'graph',
	                layout: 'force',
	                force:{
	                    repulsion:1000,
	                    edgeLength:50
	                },
	                symbolSize: 50,
	                roam: true,
	                label: {
	                    normal: {
	                        show: true
	                    }
	                },
	                edgeSymbolSize: [4, 10],
	                edgeLabel: {
	                    normal: {
	                        show:true,
	                        textStyle: {
	                            fontSize: 13
	                        },
	                        formatter: "{c}"
	                    }
	                },

	                data: createOptionData(treeData),
	                links: createLinkData(treeData),
	                lineStyle: {
	                    normal: {
	                        opacity: 0.9,
	                        width: 5,
	                        curveness: 0
	                    }
	                },
	                categories:[
	                    {name: '菜单'},
	                    {name: '页面'},
	                    {name: '按钮'}
	                ]
	            }
	        ]
	    }
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.onresize = function () {
    	myChart.resize();
    }
}

function createOptionData(treeData){
	var optionData=[];
	if(treeData && treeData.length>0){
		for(var i=0;i<treeData.length;i++){
			var data={};//第一层菜单项
			data.name=treeData[i].label;
			data.symbolSize=100;
			data.draggable=true;
			data.category=1;
			data.itemStyle={};
			data.itemStyle.normal={};
			data.itemStyle.normal.borderColor='#04f2a7';
			data.itemStyle.normal.borderWidth=6;
			data.itemStyle.normal.shadowBlur=20;
			data.itemStyle.normal.shadowColor='#04f2a7';
			data.itemStyle.normal.color='#001c43';
			optionData.push(data);
			//页面项
			if(treeData[i].children && treeData[i].children.length>0){
				for(var j=0;j<treeData[i].children.length;j++){
					var paga=(treeData[i].children)[j];
					var pageData={};
					pageData.name=paga.label;
					pageData.symbolSize=70;
					pageData.draggable=true;
					pageData.category=0;
					pageData.itemStyle={};
					pageData.itemStyle.normal={};
					pageData.itemStyle.normal.borderColor='#82dffe';
					pageData.itemStyle.normal.borderWidth=4;
					pageData.itemStyle.normal.shadowBlur=10;
					pageData.itemStyle.normal.shadowColor='#04f2a7';
					pageData.itemStyle.normal.color='#001c43';
					optionData.push(pageData);
					//按钮项
					if(paga.children && paga.children.length>0){
						for(var z=0;z<paga.children.length;z++){
							var btn=(paga.children)[z];
							var btnData={};
							btnData.name=btn.label;
							btnData.category=2;
							btnData.draggable=true;
							btnData.itemStyle={};
							btnData.itemStyle.normal={};
							btnData.itemStyle.normal.borderColor='#b457ff';
							btnData.itemStyle.normal.borderWidth=2;
							btnData.itemStyle.normal.shadowBlur=8;
							btnData.itemStyle.normal.shadowColor='#b457ff';
							btnData.itemStyle.normal.color='#001c43';
							optionData.push(btnData);
						}
					}
				}
			}
		}
	}
	return optionData;
}

function createLinkData(treeData){
	var linkData=[];
	if(treeData && treeData.length>0){
		for(var i=0;i<treeData.length;i++){
			var menu=treeData[i];
			//1.菜单相连
			for(var j=i+1;j<treeData.length;j++){
				var nextMenu=treeData[j];
				var menuLink={};
				menuLink.source=menu.label;
				menuLink.target=nextMenu.label;
				menuLink.value='';
				menuLink.lineStyle={};
				menuLink.lineStyle.normal={};
				menuLink.lineStyle.normal.color={};
				menuLink.lineStyle.normal.color.type='linear';
				menuLink.lineStyle.normal.color.x=0;
				menuLink.lineStyle.normal.color.y=0;
				menuLink.lineStyle.normal.color.x2=0;
				menuLink.lineStyle.normal.color.y2=1;
				menuLink.lineStyle.normal.color.colorStops=[];
				var a={};
				a.offset=0;
				a.color='#eda553';
				var b={};
				b.offset=1;
				b.color='#7c785b';
				menuLink.lineStyle.normal.color.colorStops.push(a);
				menuLink.lineStyle.normal.color.colorStops.push(b);
				menuLink.lineStyle.normal.color.globalCoord=false;
				linkData.push(menuLink);
			}
			//2.菜单-页面
			if(menu.children && menu.children.length>0){
				for(var j=0;j<menu.children.length;j++){
					var page=(menu.children)[j];
					var pageLink={};
					pageLink.source=menu.label;
					pageLink.target=page.label;
					pageLink.value='';
					pageLink.lineStyle={};
					pageLink.lineStyle.normal={};
					pageLink.lineStyle.normal.color={};
					pageLink.lineStyle.normal.color.type='linear';
					pageLink.lineStyle.normal.color.x=0;
					pageLink.lineStyle.normal.color.y=0;
					pageLink.lineStyle.normal.color.x2=0;
					pageLink.lineStyle.normal.color.y2=1;
					pageLink.lineStyle.normal.color.colorStops=[];
					var c={};
					c.offset=0;
					c.color='#e0f55a';
					var d={};
					d.offset=1;
					d.color='#639564';
					pageLink.lineStyle.normal.color.colorStops.push(c);
					pageLink.lineStyle.normal.color.colorStops.push(d);
					pageLink.lineStyle.normal.color.globalCoord=false;
					linkData.push(pageLink);
					//3.页面-按钮
					if(page.children && page.children.length>0){
						for(var z=0;z<page.children.length;z++){
							var btn=(page.children)[z];
							var btnLink={};
							btnLink.source=page.label;
							btnLink.target=btn.label;
							btnLink.value='';
							btnLink.lineStyle={};
							btnLink.lineStyle.normal={};
							btnLink.lineStyle.normal.color={};
							btnLink.lineStyle.normal.color.type='linear';
							btnLink.lineStyle.normal.color.x=0;
							btnLink.lineStyle.normal.color.y=0;
							btnLink.lineStyle.normal.color.x2=0;
							btnLink.lineStyle.normal.color.y2=1;
							btnLink.lineStyle.normal.color.colorStops=[];
							var e={};
							e.offset=0;
							e.color='#e0f55a';
							var f={};
							f.offset=1;
							f.color='#639564';
							btnLink.lineStyle.normal.color.colorStops.push(e);
							btnLink.lineStyle.normal.color.colorStops.push(f);
							btnLink.lineStyle.normal.color.globalCoord=false;
							linkData.push(btnLink);
						}
					}
				}
			}
		}
	}
	return linkData;
}
