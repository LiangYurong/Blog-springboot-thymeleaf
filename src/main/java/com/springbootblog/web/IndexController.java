package com.springbootblog.web;

import com.springbootblog.service.BlogService;
import com.springbootblog.service.TagService;
import com.springbootblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 前端的首页数据展示页面。先从数据库获取到数据，再传到前端。
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        //分页。"page"传到前端页面。
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }


    /**
     * 全局搜索。从博客的标题和内容进行全局搜索。
     * @param pageable
     * @param query 从index.html中的搜索框中传来的query值，该值也是全局搜索的关键词。
     * @param model
     * @return
     */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page", blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    /**
     * 跳到某一篇博客
     * @param id 代表被选中的某篇文章
     * @param model 将blog对象传到blog.html
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    /**
     * 关于我的页面展示
     * @return
     */
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    /**
     * 返回一个模板
     * 在_fragments.html的script模块有用到。
     * @return
     */
    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(4));
        return "_fragments :: newblogList";
    }
}
