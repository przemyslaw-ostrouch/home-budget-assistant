package com.przemyslawostrouch.homebudgetassistant;

import com.przemyslawostrouch.homebudgetassistant.register.RegisterManager;
import com.przemyslawostrouch.homebudgetassistant.register.RegisterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RegisterManager registerManager(RegisterRepository registerRepository) {
        return new RegisterManager(registerRepository);
    }
}
