/**
* @描述
* @文件名:ResourceRepository.java
* @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
* @描述:ResourceRepository.java
* @修改人:技术部-文章
* @修改时间:2018年12月31日 下午1:56:58
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

import com.ratel.auth.domain.Resource;

/**
 * @文件名:ResourceRepository.java
 * @版权:Copyright 2018 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述:资源实体dao层
 * @修改人:技术部-文章
 * @修改时间:2018年12月31日 下午1:56:58
 * @修改内容:新增
 */
@Repository
@Table(name = "RESOURCES")
@Qualifier("resourceRepository")
public interface ResourceRepository
		extends QueryByExampleExecutor<Resource>, CrudRepository<Resource, String>, JpaSpecificationExecutor<Resource> {

	@Query("select t from Resource t where t.id in(:ids) group by t.id")
	public List<Resource> queryResources(@Param("ids") List<String> paramList);

	/**
	 * @Title queryTopResource
	 * @author :stephen
	 * @Description 查询顶级的资源对象
	 * @date 2019年1月13日 下午8:58:43
	 * @return List<Resource>
	 */
	@Query("select t from Resource t where t.pId is null")
	public List<Resource> queryTopResource();

	/**
	 * @Title queryResourcesByPid
	 * @author :stephen
	 * @Description 根据资源上级找到所有下级
	 * @date 2019年1月13日 下午8:57:19
	 * @param pId 上级资源id
	 * @return List<Resource>
	 */
	@Query("select t from Resource t where t.pId =:pId")
	public List<Resource> queryResourcesByPid(@Param("pId") String pId);

}
