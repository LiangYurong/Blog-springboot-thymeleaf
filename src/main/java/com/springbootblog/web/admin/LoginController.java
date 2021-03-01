package com.springbootblog.web.admin;

import com.springbootblog.po.User;
import com.springbootblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 验证是否存在该用户。前端传送账户与密码，验证正确与否。
     *
     * 账户或密码错误，不能直接return "admin/login"。
     *
     * 重定向的时候不能使用Model的addAttribute方法，拿不到message信息。
     *
     * 登录成功之后，重定向到admin.html。告诉浏览器你登录成功了，建议您直接去到admin.html主页。
     *
     * @param username 用户名
     * @param password 密码
     * @param session 传送user
     * @param attributes 给前端返回错误的提醒信息
     * @return 账户密码正确则转到index.html页面，否则重定向到登录页面。
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            //密码直接传到前端页面不安全，所以设置为null
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或者密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 登出账户
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
