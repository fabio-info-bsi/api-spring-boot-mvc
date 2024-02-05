package br.com.fabex.api.authentication;

import br.com.fabex.api.services.LoggedUserManagementService;
import br.com.fabex.api.services.LoginCountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
@Slf4j
public class LoginProcessor {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    private String username;
    private String password;

    public LoginProcessor(LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    public boolean login() {
        this.loginCountService.increment();
        String username = this.getUsername();
        String password = this.getPassword();
        log.info("Referene Instance LoginProcessor: {}", this);
        boolean login = "fabex".equals(username) && "pass".equals(password);
        if(login){
            this.loggedUserManagementService.setUsername(username);
        }
        return login;
    }

}
