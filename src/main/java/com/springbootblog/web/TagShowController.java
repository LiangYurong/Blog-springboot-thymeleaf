package com.springbootblog.web;

import com.springbootblog.po.Tag;
import com.springbootblog.po.Type;
import com.springbootblog.service.BlogService;
import com.springbootblog.service.TagService;
import com.springbootblog.service.TypeService;
import com.springbootblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    /**
     * 点击博客主页的标签，则进入标签分类。
     *
     * size：每一页展现的文章数量。
     *
     * @param pageable
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Tag> tags=tagService.listTagTop(10000);
        if(id==-1){
            id=tags.get(0).getId();
        }
        BlogQuery blogQuery=new BlogQuery();
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
