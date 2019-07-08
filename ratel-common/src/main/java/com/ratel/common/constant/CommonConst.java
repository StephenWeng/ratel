/**  
* @文件名 Const.java
* @版权 Copyright 2018 版权所有：平头哥
* @描述 系统常量
* @修改人 stephen
* @修改时间 2017年5月11日 下午2:47:12
* @修改内容 新增
*/
package com.ratel.common.constant;

import org.springframework.stereotype.Component;

/**
 * 系统常量
 * 
 * @author Mr.Wang
 * @version V1.0,2017年5月11日 下午2:47:12
 * @see [相关类/方法]
 * @since V1.0
 * 
 */
@Component
public class CommonConst {

	/**
	 * cookie中存令牌的KEY
	 */
	public static final String COOKIE_KEY_JWT_TOKEN = "jwtToken";

	/**
	 * 计数 ClassName: INT_NUMBER <br/>
	 * Function: 内部类，数字类型计数 <br/>
	 * date: 2018年2月11日 上午10:37:10 <br/>
	 *
	 * @author wenzhang
	 * @version Const
	 * @see [相关类/方法]
	 * @Description:
	 * @since JDK 1.8
	 */
	public static final class INT_NUMBER {
		/**
		 * 0
		 */
		public static final int _ZERO = 0;
		/**
		 * 1
		 */
		public static final int _ONE = 1;
		/**
		 * 2
		 */
		public static final int _TWO = 2;
		/**
		 * 3
		 */
		public static final int _THREE = 3;
		/**
		 * 4
		 */
		public static final int _FOUR = 4;
		/**
		 * 5
		 */
		public static final int _FIVE = 5;
		/**
		 * 6
		 */
		public static final int _SIX = 6;
		/**
		 * 7
		 */
		public static final int _SEVEN = 7;
		/**
		 * 8
		 */
		public static final int _EIGHT = 8;
		/**
		 * 9
		 */
		public static final int _NINE = 9;
		/**
		 * 10
		 */
		public static final int _TEN = 10;
	}

	/**
	 * 
	 * ClassName: STRING_NUMBER <br/>
	 * Function: 内部类，字符串类型计数. <br/>
	 * date: 2018年2月11日 上午10:38:45 <br/>
	 *
	 * @author wenzhang
	 * @version Const
	 * @see [相关类/方法]
	 * @Description:
	 * @since JDK 1.8
	 */
	public static final class STRING_NUMBER {
		/**
		 * 0
		 */
		public static final String _ZERO = "0";
		/**
		 * 1
		 */
		public static final String _ONE = "1";
		/**
		 * 2
		 */
		public static final String _TWO = "2";
		/**
		 * 3
		 */
		public static final String _THREE = "3";
		/**
		 * 4
		 */
		public static final String _FOUR = "4";
		/**
		 * 5
		 */
		public static final String _FIVE = "5";
		/**
		 * 6
		 */
		public static final String _SIX = "6";
		/**
		 * 7
		 */
		public static final String _SEVEN = "7";
		/**
		 * 8
		 */
		public static final String _EIGHT = "8";
		/**
		 * 9
		 */
		public static final String _NINE = "9";
		/**
		 * 10
		 */
		public static final String _TEN = "10";
	}

	/**
	 * 
	 * ClassName: WEEK <br/>
	 * Function: 内部类 ，星期计数<br/>
	 * date: 2018年2月11日 上午10:40:02 <br/>
	 *
	 * @author wenzhang
	 * @version Const
	 * @see [相关类/方法]
	 * @Description:
	 * @since JDK 1.8
	 */
	public static final class WEEK {
		/**
		 * 周日
		 */
		public static final String _SUNDAY = "SUNDAY";
		/**
		 * 周一
		 */
		public static final String _MONDAY = "MONDAY";
		/**
		 * 周二
		 */
		public static final String _TUESDAY = "TUESDAY";
		/**
		 * 周三
		 */
		public static final String _WEDNESDAY = "WEDNESDAY";
		/**
		 * 周四
		 */
		public static final String _THURSDAY = "THURSDAY";
		/**
		 * 周五
		 */
		public static final String _FRIDAY = "FRIDAY";
		/**
		 * 周六
		 */
		public static final String _SATURDAY = "SATURDAY";
	}

	/**
	 * 
	 * ClassName: LOG_TYPE <br/>
	 * Function: 内部类，日志类型. <br/>
	 * date: 2018年2月11日 上午10:43:14 <br/>
	 *
	 * @author wenzhang
	 * @version Const
	 * @see [相关类/方法]
	 * @Description:
	 * @since JDK 1.8
	 */
	public static final class LOG_TYPE {
		/**
		 * 
		 * ClassName: _SERVICE <br/>
		 * date: 2018年2月11日 上午10:43:27 <br/>
		 *
		 * @author wenzhang
		 * @version Const.LOG_TYPE
		 * @see [相关类/方法]
		 * @Description:
		 * @since JDK 1.8
		 */
		public static final class _SERVICE {
			/**
			 * 添加日志
			 */
			public static final String _ADD = "Service Log(ADD) ";
			/**
			 * 删除日志
			 */
			public static final String _DELETE = "Service Log(DELETE) ";
			/**
			 * 修改日志
			 */
			public static final String _MODIFY = "Service Log(MODIFY) ";
			/**
			 * 查询日志
			 */
			public static final String _SEARCH = "Service Log(SEARCH) ";
		}

		/**
		 * 
		 * ClassName: _RUNTIME <br/>
		 * date: 2018年2月11日 上午10:44:06 <br/>
		 *
		 * @author wenzhang
		 * @version Const.LOG_TYPE
		 * @see [相关类/方法]
		 * @Description:
		 * @since JDK 1.8
		 */
		public static final class _RUNTIME {
			/**
			 * 错误日志
			 */
			public static final String _Error = "Runtime Log(Error) ";
		}
	}

}
