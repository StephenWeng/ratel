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