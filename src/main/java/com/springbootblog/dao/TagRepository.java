package com.springbootblog.dao;

import com.springbootblog.po.Tag;
import com.springbootblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);

    /**
     * 首页展示的标签项目是10个，按照博客数量倒序排序
     *
     * 自定义SQL语句
     * @param pageable
     * @return
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
