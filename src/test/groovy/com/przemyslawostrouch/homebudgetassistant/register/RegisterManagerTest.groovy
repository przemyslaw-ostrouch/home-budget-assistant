package com.przemyslawostrouch.homebudgetassistant.register


import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import spock.lang.Specification

class RegisterManagerTest extends Specification {

    def "should return correct account balances"() {
        given:
        def registers = []
        RegisterRepository registerRepository = Stub(RegisterRepository.class)
        RegisterFinder registerFinder = new RegisterFinder(registerRepository)
        RegisterTransactionManager transactionManager = Stub(RegisterTransactionManager.class)
        RegisterManager registerManager = new RegisterManager(registerRepository, registerFinder, transactionManager)
        Register firstRegister = createMockRegister(1L, BigDecimal.valueOf(100))
        Register secondRegister = createMockRegister(1L, BigDecimal.valueOf(100))
        Register thirdRegister = createMockRegister(1L, BigDecimal.valueOf(100))
        registers << firstRegister << secondRegister << thirdRegister

        registerRepository.findAll() >> registers

        when:
        def result = registerManager.getAll()

        then:
        result.size() == 3
        result.containsAll(registers)
    }

    static Register createMockRegister(Long id, BigDecimal balance) {
        Register.builder()
                .id(id)
                .name('name' + balance)
                .balance(new Balance(balance))
                .build()
    }
}