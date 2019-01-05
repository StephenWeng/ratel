/**
* @描述
* @文件名:DepartmentRepository.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:DepartmentRepository.java
* @修改人:stephen
* @修改时间:2019年1月2日 下午9:13:54
* @修改内容:新增
*/
package com.ratel.auth.repository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public interface DepartmentRepository extends QueryByExampleExecutor<Department>, CrudRepository<Department, String>,
		JpaSpecificationExecutor<Department> {

	/**
	 * @Title queryTopDepartment
	 * @author :Stephen
	 * @Description 查询最顶级机构
	 * @date 2019年1月3日 下午2:29:34
	 * @return Department
	 */
	@Query("select t from Department t where t.pId is null")
	public List<Department> queryTopDepartment();

	/**
	 * @Title checkName
	 * @author :stephen
	 * @Description 查询某部门下级使用name的单位
	 * @date 2019年1月5日 下午10:52:12
	 * @param name
	 *            名称
	 * @param pId
	 *            上级部门id
	 * @return List<Department>
	 */
	@Query("select t from Department t where t.name=:name and t.pId=:pId")
	public List<Department> checkName(@Param("name") String name, @Param("pId") String pId);

	/**
	 * @Title checkName
	 * @author :stephen
	 * @Description 查询某部门下级除了自己使用name的单位
	 * @date 2019年1月5日 下午10:52:12
	 * @param name
	 *            名称
	 * @param pId
	 *            上级部门id
	 * @param id
	 *            本部门id
	 * @return List<Department>
	 */
	@Query("select t from Department t where t.name=:name and t.pId=:pId and t.id!=:id")
	public List<Department> checkName(@Param("name") String name, @Param("pId") String pId, @Param("id") String id);

	/**
	 * @Title checkCode
	 * @author :stephen
	 * @Description 查询某部门下级使用code的单位
	 * @date 2019年1月5日 下午10:52:12
	 * @param code
	 *            编码
	 * @param pId
	 *            上级部门id
	 * @return List<Department>
	 */
	@Query("select t from Department t where t.code=:code and t.pId=:pId")
	public List<Department> checkCode(@Param("code") String code, @Param("pId") String pId);

	/**
	 * @Title checkCode
	 * @author :stephen
	 * @Description 查询某部门下级除了自己使用code的单位
	 * @date 2019年1月5日 下午10:52:12
	 * @param code
	 *            编码
	 * @param pId
	 *            上级部门id
	 * @param id
	 *            本部门id
	 * @return List<Department>
	 */
	@Query("select t from Department t where t.code=:code and t.pId=:pId and t.id!=:id")
	public List<Department> checkCode(@Param("code") String code, @Param("pId") String pId, @Param("id") String id);

	@Modifying
	@Transactional
	@Query("delete from Department t where t.id in (:ids)")
	public int delInBatch(@Param("ids") List<String> ids);

}
