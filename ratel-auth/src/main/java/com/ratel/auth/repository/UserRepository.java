package com.ratel.auth.repository;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.ratel.auth.domain.User;

/**
 * @文件名:UserRepository.java
 * @版权:Copyright 2018 版权所有：平头哥
 * @描述:用户信息数据持久层
 * @修改人:Stephen
 * @修改时间:2018年12月13日 下午4:30:13
 * @修改内容:新增
 */
@Repository
@Table(name = "USER")
@Qualifier("userRepository")
public interface UserRepository
		extends QueryByExampleExecutor<User>, CrudRepository<User, String>, JpaSpecificationExecutor<User> {

	/**
	 * 
	 * @Title findByUserAccount
	 * @author :Stephen
	 * @Description 根据用户名查询用户
	 * @date 2018年12月22日 上午10:31:57
	 * @param account 用户账号
	 * @return User 用户全部信息
	 */
	@Query("select t from User t where t.account=:account and t.isDeleted=0")
	public User findByUserAccount(@Param("account") String account);

}
