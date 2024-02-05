package br.com.fabex.api.services;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Getter
@Service
@ApplicationScope
public class LoginCountService {

    private int count;

    public void increment() {
        count++;
    }

}
