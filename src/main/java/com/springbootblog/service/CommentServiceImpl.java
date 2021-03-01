package com.springbootblog.service;

import com.springbootblog.dao.CommentRepository;
import com.springbootblog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService, Serializable {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
        List<Comment> comments=commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    /**
     * 如果当前的“评论”有父类,则需要更新parentCommentId
     * @param comment
     * @return
     */
    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if(parentCommentId!=-1){
            comment.setParentComment(commentRepository.findById(parentCommentId).get());
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView=new ArrayList<>();
        for(Comment comment:comments){
            Comment c=new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    private void combineChildren(List<Comment> comments){
        for(Comment comment:comments){
            List<Comment> replys1=comment.getReplyComments();
            for(Comment reply1:replys1){
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys=new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys=new ArrayList<>();

    /**
     * 递归迭代
     * @param comment 被迭代的对象
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);
        if (comment.getReplyComments().size()>0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                recursively(reply);
            }
        }
    }
}