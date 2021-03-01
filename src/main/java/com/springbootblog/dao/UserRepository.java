package com.springbootblog.dao;

import com.springbootblog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 根据账户密码查询是否有该用户。
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);
}
