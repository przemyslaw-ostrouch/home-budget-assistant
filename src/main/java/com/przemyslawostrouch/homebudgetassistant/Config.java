package com.przemyslawostrouch.homebudgetassistant;

import com.przemyslawostrouch.homebudgetassistant.register.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RegisterManager registerManager(RegisterRepository registerRepository, RegisterFinder registerFinder) {
        return new RegisterManager(registerRepository, registerFinder);
    }

    @Bean
    public RegisterFinder registerFinder(RegisterRepository registerRepository) {
        return new RegisterFinder(registerRepository);
    }

    @Bean
    public TransferManager transferManager(
            RegisterRepository registerRepository,
            TransactionRepository transactionRepository,
            RegisterFinder registerFinder
    ) {
        return new TransferManager(registerRepository, transactionRepository, registerFinder);
    }
}
