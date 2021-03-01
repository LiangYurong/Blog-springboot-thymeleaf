package com.springbootblog;

import com.springbootblog.po.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBlogApplicationTests {

    @Autowired
    private Blog blog;

    @Test
    void contextLoads() {
        System.out.println(blog);
    }

}
