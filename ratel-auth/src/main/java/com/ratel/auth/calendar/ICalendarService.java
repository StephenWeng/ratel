/**  
* @描述 
* @文件名:ICalendarService.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:ICalendarService.java
* @修改人:Stephen
* @修改时间:2019年5月23日 上午10:35:17
* @修改内容:新增
*/
package com.ratel.auth.calendar;

import com.ratel.common.response.ResponseData;

/**  
* @描述 
* @文件名:ICalendarService.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:ICalendarService.java
* @修改人:Stephen
* @修改时间:2019年5月23日 上午10:35:17
* @修改内容:新增
*/
/**
 * @className ICalendarService
 * @author :Stephen
 * @Description
 * @date 2019年5月23日 上午10:35:17
 */
public interface ICalendarService {

	public ResponseData queryEvents(String date);

}
