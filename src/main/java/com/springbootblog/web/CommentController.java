package com.springbootblog.web;

import com.springbootblog.po.Comment;
import com.springbootblog.po.User;
import com.springbootblog.service.BlogService;
import com.springbootblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${visitorAvatar}")
    private String visitorAvatar;

    @Value("${adminAvatar}")
    private String adminAvatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    /**
     * 提交评论
     *
     * seesion是获取用户的
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {

        Long blogId=comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user=(User)session.getAttribute("user");
        System.out.println(user+"那就恩文件呢文件呢文件备忘");
        if(user!=null){
            comment.setAvatar(adminAvatar);
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(visitorAvatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

}
