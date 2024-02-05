package br.com.fabex.api.controller;

import br.com.fabex.api.services.LoggedUserManagementService;
import br.com.fabex.api.services.LoginCountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class HomeController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    @RequestMapping("/static-home")
    public String getStaticHome() {
        return "home.html";
    }

    @RequestMapping("/dynamic-home")
    public String getDynamicHome(@RequestParam(required = false) String color, Model page) {
        page.addAttribute("username", "Fabex");
        page.addAttribute("color", Objects.nonNull(color) ? color : "red");
        return "home.html";
    }

    @RequestMapping("/dynamic-home/{color}")
    public String getDynamicHomeWithPathVariable(@PathVariable String color, Model page) {
        page.addAttribute("username", "Fabex");
        page.addAttribute("color", Objects.nonNull(color) ? color : "red");
        return "home.html";
    }

    @GetMapping("/home")
    public String home(Model page) {
        String username = this.loggedUserManagementService.getUsername();
        if(Objects.isNull(username)){
            return "redirect:/";
        }
        var loginCount = this.loginCountService.getCount();
        page.addAttribute("username", username);
        page.addAttribute("loginCount", loginCount);
        return "home.html";
    }
}
