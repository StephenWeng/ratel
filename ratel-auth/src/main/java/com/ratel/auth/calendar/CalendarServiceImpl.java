/**  
* @描述 
* @文件名:CalendarServiceImpl.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:CalendarServiceImpl.java
* @修改人:Stephen
* @修改时间:2019年5月23日 上午10:35:45
* @修改内容:新增
*/
package com.ratel.auth.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratel.common.response.ResponseData;
import com.ratel.common.response.ResponseMsg;

/**  
* @描述 
* @文件名:CalendarServiceImpl.java
* @版权:Copyright 2018 版权所有：平头哥
* @描述:CalendarServiceImpl.java
* @修改人:Stephen
* @修改时间:2019年5月23日 上午10:35:45
* @修改内容:新增
*/
/**
 * @className CalendarServiceImpl
 * @author :Stephen
 * @Description
 * @date 2019年5月23日 上午10:35:45
 */
@Service
public class CalendarServiceImpl implements ICalendarService {

	@Autowired
	private CalendarRepository calendarRepository;

	/**
	 * @Title queryEvents
	 * @author :Stephen
	 * @Description
	 * @date 2019年5月23日 下午3:56:55
	 * @param date
	 * @return
	 */
	@Override
	public ResponseData queryEvents(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		List<Calendar> calendars = calendarRepository.queryEvents(date);
		List<EventVo> list = new ArrayList<EventVo>();
		for (Calendar calendar : calendars) {
			EventVo vo = new EventVo(calendar.getId(), calendar.getTitle(), sdf.format(calendar.getStartTime()),
					sdf.format(calendar.getEndTime()), true, calendar.getUrl(), calendar.getStatus());
			list.add(vo);
		}
		return new ResponseData(ResponseMsg.SUCCESS, list);
	}

}
