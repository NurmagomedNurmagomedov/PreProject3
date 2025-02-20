package space.nurik.preproject3.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.nurik.preproject3.task3.security.UserDetailsImp;
import space.nurik.preproject3.task3.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String me(Model model) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetailsImp id = (UserDetailsImp) (authentication.getPrincipal());

        model.addAttribute("user", userService.findOne(id.getUser().getId()));
        return "user/show";
    }

}
