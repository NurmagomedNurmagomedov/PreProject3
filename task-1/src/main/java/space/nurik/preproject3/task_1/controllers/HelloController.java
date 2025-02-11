package space.nurik.preproject3.task_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping
    public String redirect() {
        return "redirect:/users";
    }
}
