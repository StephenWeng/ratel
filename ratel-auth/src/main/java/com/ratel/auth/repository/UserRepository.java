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

	@Query("select t from User t where t.email=:email and t.isDeleted=0")
	public User findByEmail(@Param("email") String email);

	@Query("select t from User t where t.email=:email and t.account!=:account and t.isDeleted=0")
	public List<User> findEmailAnyotherAccount(@Param("account") String account, @Param("email") String email);

	@Modifying
	@Transactional
	@Query("delete from User t where t.id in (:ids)")
	public int delInBatch(@Param("ids") List<String> ids);

	@Query("select t from User t where t.name=:name and t.isDeleted=0")
	public List<User> checkName(@Param("name") String name);

	@Query("select t from User t where t.name=:name and t.id!=:id and t.isDeleted=0")
	public List<User> checkName(@Param("name") String name, @Param("id") String id);

	@Query("select t from User t where t.account=:account and t.isDeleted=0")
	public List<User> checkAccount(@Param("account") String account);

	@Query("select t from User t where t.account=:account and t.id!=:id and t.isDeleted=0")
	public List<User> checkAccount(@Param("account") String account, @Param("id") String id);

	@Query("select t from User t where t.email=:email and t.isDeleted=0")
	public List<User> checkEmail(@Param("email") String email);

	@Query("select t from User t where t.email=:email and t.id!=:id and t.isDeleted=0")
	public List<User> checkEmail(@Param("email") String email, @Param("id") String id);

}
