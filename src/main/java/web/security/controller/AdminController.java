package web.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.security.model.Role;
import web.security.model.User;
import web.security.service.RoleService;
import web.security.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

    @GetMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("AllRoles", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/newUser")
    public String createUser(@ModelAttribute("user") User user,
                         @RequestParam("roles") String[] nameRole) {
        user.setRoles(roleService.getSetOfRoles(nameRole));
        userService.saveNewUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("AllRoles", roleService.getAllRoles());
        return "user-update";
    }

    @PostMapping("/updateUser/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("roles") String[] nameRole) {
        user.setRoles(roleService.getSetOfRoles(nameRole));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
