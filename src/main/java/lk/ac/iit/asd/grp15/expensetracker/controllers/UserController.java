package lk.ac.iit.asd.grp15.expensetracker.controllers;


import lk.ac.iit.asd.grp15.expensetracker.services.ISecurityService;
import lk.ac.iit.asd.grp15.expensetracker.services.IUserService;
import lk.ac.iit.asd.grp15.expensetracker.models.User;
import lk.ac.iit.asd.grp15.expensetracker.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final ISecurityService securityService;

    private final UserValidator userValidator;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/transactions";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
