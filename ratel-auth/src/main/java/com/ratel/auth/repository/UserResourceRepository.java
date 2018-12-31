/**
* @描述
* @文件名:UserResourceRepository.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:UserResourceRepository.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午2:48:05
* @修改内容:新增
*/
package com.ratel.auth.repository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.ratel.auth.domain.UserResources;

/**
 * @描述
 * @文件名:UserResourceRepository.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:UserResourceRepository.java
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午2:48:05
 * @修改内容:新增
 */
@Repository
@Table(name = "USER_RESOURCES")
@Qualifier("userResourceRepository")
public interface UserResourceRepository extends QueryByExampleExecutor<UserResources>,
		CrudRepository<UserResources, String>, JpaSpecificationExecutor<UserResources> {

	@Query("select t from UserResources t where t.userId=:userId")
	public List<UserResources> findByUserid(@Param("userId") String userId);

}
