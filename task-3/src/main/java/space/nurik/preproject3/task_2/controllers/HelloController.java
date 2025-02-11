package space.nurik.preproject3.task_2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String redirect() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Hello контроллер, перенаправление user на /user, admin на /admin");
        for (GrantedAuthority authority : auth.getAuthorities()) {
            System.out.println("Роль-" + authority.getAuthority());

        }
        if (auth.getAuthorities().stream()
                .anyMatch(gAuth -> gAuth.getAuthority().equals("ROLE_ADMIN")))
            return "redirect:/admin";
        else return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
