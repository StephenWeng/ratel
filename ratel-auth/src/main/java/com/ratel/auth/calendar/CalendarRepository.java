/**
* @描述
* @文件名:DepartmentRepository.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:DepartmentRepository.java
* @修改人:stephen
* @修改时间:2019年1月2日 下午9:13:54
* @修改内容:新增
*/
package com.ratel.auth.calendar;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.ratel.auth.domain.Department;

/**
 * @文件名:DepartmentRepository.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:部门操作dao层
 * @修改人:stephen
 * @修改时间:2019年1月2日 下午9:13:54
 * @修改内容:新增
 */
@Repository
@Table(name = "DEPARTMENTS")
@Qualifier("departmentRepository")
public interface CalendarRepository extends QueryByExampleExecutor<Department>, CrudRepository<Department, String>,
		JpaSpecificationExecutor<Department> {

	/**
	 * @Title checkCode
	 * @author :stephen
	 * @Description 查询某部门下级使用code的单位
	 * @date 2019年1月5日 下午10:52:12
	 * @param code 编码
	 * @param pId  上级部门id
	 * @return List<Department>
	 */
	@Query("select t from Calendar t where STR_TO_DATE(date_format(START_TIME, '%Y-%m'),'%Y-%m') = STR_TO_DATE(:date,'%Y-%m')")
	public List<Calendar> queryEvents(@Param("date") String date);

}
