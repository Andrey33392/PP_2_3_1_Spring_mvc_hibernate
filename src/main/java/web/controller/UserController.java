package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute ("adduser") User user) {

        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("adduser") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors())
            return "new";
        userService.addUser(user);
        return "redirect: /";
    }

    @GetMapping("/edit/{id}")
    public String getUsersById(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors())
            return "edit";
        userService.updateUser(user);
        return "redirect: /";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        userService.removeUser(id);
        return "redirect: /";
    }
}
