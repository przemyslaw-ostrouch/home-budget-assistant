package com.przemyslawostrouch.homebudgetassistant.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    @Bean
    RegisterManager registerManager(RegisterRepository registerRepository, TransactionRepository transactionRepository) {
        return new RegisterManager(registerRepository, new RegisterFinder(registerRepository), new RegisterTransactionManager(transactionRepository));
    }

    @Bean
    TransferManager transferManager(RegisterRepository registerRepository, TransactionRepository transactionRepository) {
        return new TransferManager(registerRepository, new RegisterFinder(registerRepository), new RegisterTransactionManager(transactionRepository));
    }
}
