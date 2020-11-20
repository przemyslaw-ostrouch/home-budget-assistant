package com.przemyslawostrouch.homebudgetassistant.register

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance
import com.przemyslawostrouch.homebudgetassistant.register.dto.MoneyValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import spock.lang.Specification

class RegisterManagerTest extends Specification {

    def "should return correct account balance"() {
        given:
        RegisterRepository registerRepository = Stub(RegisterRepository.class)
        RegisterManager registerManager = new RegisterManager(registerRepository)
        Register registerToReturn = createMockRegister()
        registerRepository.findById(1L) >> Optional.of(registerToReturn)

        when:
        def result = registerManager.getBalance(1L)

        then:
        result.value == 1000
        result.currency == MoneyValue.Currency.PLN
    }

    def "should fail when Register account doesnt exist"() {
        given:
        RegisterRepository registerRepository = Stub(RegisterRepository.class)
        RegisterManager registerManager = new RegisterManager(registerRepository)
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
                .balance(createMockBalance())
                .build()
    }

    static Balance createMockBalance() {
        Balance.builder()
                .value(BigDecimal.valueOf(1000))
                .currency(MoneyValue.Currency.PLN)
                .build()
    }
}