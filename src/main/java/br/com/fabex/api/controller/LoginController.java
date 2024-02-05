package br.com.fabex.api.controller;

import br.com.fabex.api.authentication.LoginProcessor;
import br.com.fabex.api.services.LoggedUserManagementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {

    private LoginProcessor loginProcessor;
    private LoggedUserManagementService loggedUserManagementService;

    @GetMapping("/")
    public String loginGet() {
        return "login.html";
    }

    @PostMapping
    public String loginPost(@RequestParam String username, @RequestParam String password,
                            Model page) {
        this.loginProcessor.setUsername(username);
        this.loginProcessor.setPassword(password);
        boolean login = this.loginProcessor.login();
        if (login) {
            this.loggedUserManagementService.setUsername(username);
            return "redirect:/home";
        }
        page.addAttribute("message", "Falhou!");
        return "login.html";
    }

}
