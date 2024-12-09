package com.studyless.studylesskidscrm.Controllers;

import com.studyless.studylesskidscrm.Models.User;
import com.studyless.studylesskidscrm.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationsController {
    private UserService userService;
    @Autowired
    public RegistrationsController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "User/registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult result, Model model) {


        if(result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage",result.getFieldError().getDefaultMessage());
            return "User/registration";
        }

        try{
            if(userService.saveUser(user)) {
                return "redirect:/index";
            }
            else{
                model.addAttribute("errorMessage", "User already exists");
            }
        }catch (Exception ex){
            model.addAttribute("errorMessage", ex.getMessage());
            return "User/registration";
        }

        return  "User/registration";

    }
}
