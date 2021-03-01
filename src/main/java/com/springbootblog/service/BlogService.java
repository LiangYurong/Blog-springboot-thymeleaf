package com.springbootblog.service;

import com.springbootblog.po.Blog;
import com.springbootblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

public interface BlogService {
    /**
     * 获取
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    /**
     * 获取一组博客数据
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * 全局搜索功能
     * @param query
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     * 推荐博客列表
     * @param size
     * @return
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /** 归档功能，统计所有的博客文章数量 */
    Long CountBlog();

    /**
     * 主要用于归档功能
     * String对应年份，List对应年份下面所有的博客
     * @return
     */
    Map<String,List<Blog>> archiveBlog();

    /**
     * 保存新博客
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新博客
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id,Blog blog);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(Long id);
}
