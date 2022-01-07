package web.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.security.model.User;
import web.security.service.RoleService;
import web.security.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user_info")
    public String getUserInfo(Authentication authentication, Model model) {
        User user = userService.findByUserName(authentication.getName());
        model.addAttribute("user", user);
        return "userInfo";
    }
}
