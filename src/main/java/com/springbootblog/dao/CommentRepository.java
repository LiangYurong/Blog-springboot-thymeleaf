package com.springbootblog.dao;

import com.springbootblog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long>, JpaSpecificationExecutor<Comment> {

    /**
     * 评论按照最新时间进行排序
     * @param blogId
     * @param sort
     * @return
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}
