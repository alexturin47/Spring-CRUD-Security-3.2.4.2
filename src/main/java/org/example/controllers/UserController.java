package org.example.controllers;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleServce;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleServce roleServce;

    @Autowired
    public UserController(UserService userService, RoleServce roleServce) {
        this.userService = userService;
        this.roleServce = roleServce;
    }

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }


    @GetMapping("/admin")
    public String adminPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.index());
        model.addAttribute("roles", roleServce.index());
        return "/admin";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if(user.getRolesAsString().contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }

    }

    @GetMapping("/user")
    public String pageForReadProfile(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        return "user";
    }

    @PostMapping("/admin/")
    public String create(@ModelAttribute("user") User user, @RequestParam("roles") String[] roles) {
        user.setRoles(getRoleSet(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    private HashSet<Role> getRoleSet(String[] roles) {
        HashSet<Role> resSet = new HashSet<>();
        for( String roleName: roles) {
            resSet.add(roleServce.findByRolename(roleName));
        }
        return resSet;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("roles", roleServce.index());
        return "/create";
    }

    @GetMapping("/admin/user/{id}")
    public String showUser(Model model, Principal principal, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.read(id));
        model.addAttribute("roles", roleServce.index());
        model.addAttribute("owner", userService.findByUsername(principal.getName()));
        return "/user";
    }

    @GetMapping("/admin/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.read(id));
        model.addAttribute("roles", roleServce.index());
        return "/delete";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.read(id));
        model.addAttribute("roles", roleServce.index());
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String updateAdmin(@ModelAttribute("user") User user, @PathVariable("id") Long id
                        ,@RequestParam("roles") String... roles) {
        user.setRoles(getRoleSet(roles));
        userService.update(id, user);
        return "redirect:/authenticated";
    }

}
