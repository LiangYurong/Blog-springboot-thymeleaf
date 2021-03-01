package com.springbootblog.service;

import com.springbootblog.po.Comment;
import java.util.*;

public interface CommentService {

    //获取之前评论的列表
    List<Comment>  listCommentByBlogId(Long blogId);

    //保存传递过来的Comment对象
    Comment saveComment(Comment comment);
}
