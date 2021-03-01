package com.springbootblog.dao;

import com.springbootblog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JpaRepository：简单查询
     * JpaSpecificationExecutor: JpaSpecificationExecutor封装了复杂查询和方法
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    /**
     * 首页展示的博客推荐是10个，按照最新更新时间倒序排序
     *
     * 自定义SQL语句
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.recommend= true ")
    List<Blog> findTop(Pageable pageable);

    /**
     * 在归档版块展示所有博客，获取博客年份并展示年份下的所有博客。根绝创建时间展示，而不是更新时间。
     * 查询到所有博客的所有年份。
     * @return
     */
    @Query("select function ('date_format',b.createTime,'%Y') as year from Blog b group by function ('date_format',b.createTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    /** 根据年份查询到所有的博客 */
    @Query("select b from Blog b where function('date_format',b.createTime,'%Y') = ?1 and b.published=true")
    List<Blog> findByYear(String year);

    /**
     * ？1代表第一个参数query。
     *
     * 从博客的标题以及内容查找是否含有关键词
     *
     * mysql：select * from t_blog b where b.title like '%关键词%' or b.content like '%关键词%'
     *
     * @param query 全局搜索的关键词
     * @param pageable
     * @return
     */
    @Query("select  b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views =b.views+1 where b.id = ?1 ")
    int updateViews(Long id);
}
