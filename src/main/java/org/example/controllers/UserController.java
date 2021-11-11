package org.example.controllers;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.index());
        return "/admin";
    }

    @GetMapping("/authentificated")
    public String pageForAuthentificatedUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.index());
        return "/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.read(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }
}
