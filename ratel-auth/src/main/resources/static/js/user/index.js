function createDepartmentRadarEcharts(id,treeData){
	var myChart = echarts.init(document.getElementById(id));
	var scaleData=[];
	var scaleData=getAllDepartment(scaleData,treeData);//获取全部的部门结构
	var rich = {
	    white: {
	        color: '#ddd',
	        align: 'center',
	        padding: [5, 0]
	    }
	};
	var placeHolderStyle = {
	    normal: {
	        label: {
	            show: false
	        },
	        labelLine: {
	            show: false
	        },
	        color: 'rgba(0, 0, 0, 0)',
	        borderColor: 'rgba(0, 0, 0, 0)',
	        borderWidth: 0
	    }
	};
	var data = [];
	for (var i = 0; i < scaleData.length; i++) {
	    data.push({
	        value: scaleData[i].value,
	        name: scaleData[i].name,
	        itemStyle: {
	            normal: {
	                borderWidth: 5,
	                shadowBlur: 30,
	                borderColor: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
	                    offset: 0,
	                    color: '#7777eb'
	                }, {
	                    offset: 1,
	                    color: '#70ffac'
	                }]),
	                shadowColor: 'rgba(142, 152, 241, 0.6)'
	            }
	        }
	    }, {
	        value: 4,
	        name: '',
	        itemStyle: placeHolderStyle
	    });
	}
	var seriesObj = [{
	    name: '',
	    type: 'pie',
	    clockWise: false,
	    radius: [195, 200],
	    hoverAnimation: false,
	    itemStyle: {
	        normal: {
	            label: {
	                show: true,
	                position: 'outside',
	                color: 'black',
	                formatter: function(params) {
	                    var percent = 0;
	                    var total = 0;
	                    for (var i = 0; i < scaleData.length; i++) {
	                        total += scaleData[i].value;
	                    }
	                    percent = ((params.value / total) * 100).toFixed(0);
	                    if(params.name !== '') {
	                        return params.name;
	                    }else {
	                        return '';
	                    }
	                },
	                rich: rich
	            },
	            labelLine: {
	                show: false
	            }
	        }
	    },
	    data: data
	}];
	option = {
	    backgroundColor: '#D3DBE3',
	    tooltip: {
	        show: false
	    },
	    legend: {
	        show: false
	    },
	    toolbox: {
	        show: false
	    },
	    series: seriesObj
	}
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.onresize = function () {
    	myChart.resize();
    }
}

function getAllDepartment(scaleData,treeData){
	if(treeData && treeData.length>0){
		for(var i=0;i<treeData.length;i++){
			var scale={};
			var data=treeData[i];
			scale.name=data.label;
			scaleData.push(scale);
			getAllDepartment(scaleData,data.children);
		}
	}
	//为每个scale的value值赋值
	for(var i=0;i<scaleData.length;i++){
		var scale=scaleData[i];
		scale.value=100/scaleData.length;
	}
	return scaleData;
}
