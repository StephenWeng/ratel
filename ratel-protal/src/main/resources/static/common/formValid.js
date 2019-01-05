/**
 * 检测输入框为空或者为空字符串
 */
var checkEmpty = (rule, value, callback) => {
	if (value === '' || value.trim()==='') {
  		callback(new Error('可不能输入空内容哦'));
	} else {
  		callback();
	}
};

//验证手机号码
var checkPhone= (rule, value, callback) => {
	if(value==='' || value.trim()===''){
		callback();
	}
	if(telphoneReg.test(value.trim())){
		callback();
		}else{
			callback(new Error('请输入正确的电话号码'));
		}
};

//验证座机号
var checkPlanePhone= (rule, value, callback) => {
	if(value==='' || value.trim()===''){
		callback();
	}
	if(planePhoneReg.test(value.trim())){
		callback();
	}else{
		callback(new Error('请输入正确的座机号码'));
	}
};

//只能输入汉字和英文字母
var checkOnlyEnCn= (rule, value, callback) => {
	if(value==='' || value.trim()===''){
		callback();
	}
	if(normalInputReg.test(value.trim())){
		callback();
	}else{
		callback(new Error('只能输入汉字和英文字母'));
	}
};

var checkNoSpecialCharacters= (rule, value, callback) => {
	if(value==='' || value.trim()===''){
		callback();
	}
	if(specialCharactersReg.test(value.trim())){
		callback();
	}else{
		callback(new Error('不能包含特殊字符或空格'));
	}
};