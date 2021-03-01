package com.springbootblog.dao;

import com.springbootblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {
    /**
     * 通过名字找到Type
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 首页展示的分类项目是6个，按照博客数量倒序排序
     *
     * 自定义SQL语句
     * @param pageable
     * @return
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
