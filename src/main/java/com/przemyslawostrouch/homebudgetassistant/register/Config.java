package com.przemyslawostrouch.homebudgetassistant.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RegisterTransactionManager registerTransactionManager(TransactionRepository transactionRepository) {
        return new RegisterTransactionManager(transactionRepository);
    }

    @Bean
    public RegisterManager registerManager(RegisterRepository registerRepository, RegisterFinder registerFinder, RegisterTransactionManager registerTransactionManager) {
        return new RegisterManager(registerRepository, registerFinder, registerTransactionManager);
    }

    @Bean
    public RegisterFinder registerFinder(RegisterRepository registerRepository) {
        return new RegisterFinder(registerRepository);
    }

    @Bean
    public TransferManager transferManager(RegisterRepository registerRepository, RegisterTransactionManager registerTransactionManager, RegisterFinder registerFinder) {
        return new TransferManager(registerRepository, registerFinder, registerTransactionManager);
    }
}
