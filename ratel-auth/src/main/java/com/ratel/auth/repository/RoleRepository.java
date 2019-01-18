/**
* @描述
* @文件名:RoleRepository.java
* @版权:Copyright 2019 版权所有：平头哥
* @描述:RoleRepository.java
* @修改人:stephen
* @修改时间:2019年1月13日 上午12:41:58
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

import com.ratel.auth.domain.Role;

/**
 * @文件名:RoleRepository.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:角色实体dao层
 * @修改人:stephen
 * @修改时间:2019年1月13日 上午12:41:58
 * @修改内容:新增
 */
@Repository
@Table(name = "ROLES")
@Qualifier("roleRepository")
public interface RoleRepository
		extends QueryByExampleExecutor<Role>, CrudRepository<Role, String>, JpaSpecificationExecutor<Role> {

	@Modifying
	@Transactional
	@Query("delete from Role t where t.id in (:ids)")
	public int delInBatch(@Param("ids") List<String> ids);

	@Query("select t from Role t where t.id in(:ids)")
	public List<Role> queryRoles(@Param("ids") List<String> ids);

	@Query("select t from Role t where t.name=:name")
	public List<Role> checkName(@Param("name") String name);

	@Query("select t from Role t where t.name=:name and t.id!=:id")
	public List<Role> checkName(@Param("name") String name, @Param("id") String id);
}
