
package com.ratel.common.response;

public enum ResponseMsg {
	SUCCESS("000000", "操作成功"), FAILED("999999", "操作失败"), ILLEGAL_PARAM("000001", "非法的参数"), NO_MAPPING_RESULT("000002",
			"没有对应的结果数据"), REF_OBJ_NOT_EXISIT("000003", "查询的数据不存在"), OBJ_BEEN_USED("000004", "已经被使用"), DELETE_FORBIDDEN(
					"000005", "不允许被删除"), MODIFY_FORBIDDEN("000006", "不允许被修改"), CLASS_CAST_ERROR("000007",
							"参数类型转换错误"), DATABASE_OPERATION_ERROR("000008", "数据库操作错误");
	private ResponseMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
