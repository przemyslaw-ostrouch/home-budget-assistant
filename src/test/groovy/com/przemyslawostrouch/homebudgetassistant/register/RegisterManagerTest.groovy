package com.przemyslawostrouch.homebudgetassistant.register

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import spock.lang.Specification

class RegisterManagerTest extends Specification {

    def "should return correct account balance"() {
        given:
        RegisterRepository registerRepository = Stub(RegisterRepository.class)
        RegisterFinder registerFinder = new RegisterFinder(registerRepository)
        RegisterManager registerManager = new RegisterManager(registerRepository, registerFinder)
        Register registerToReturn = createMockRegister()
        registerRepository.findById(1L) >> Optional.of(registerToReturn)

        when:
        def result = registerManager.getBalance(1L)

        then:
        result.balance.value == 1000
    }

    def "should fail when Register account doesnt exist"() {
        given:
        RegisterRepository registerRepository = Stub(RegisterRepository.class)
        RegisterFinder registerFinder = new RegisterFinder(registerRepository)
        RegisterManager registerManager = new RegisterManager(registerRepository, registerFinder)
        registerRepository.findById(1L) >> Optional.empty()

        when:
        registerManager.getBalance(1L)

        then:
        thrown(RuntimeException)
    }

    static Register createMockRegister() {
        Register.builder()
                .id(1L)
                .name('Wallet')
                .balance(new Balance(BigDecimal.valueOf(1000)))
                .build()
    }
}