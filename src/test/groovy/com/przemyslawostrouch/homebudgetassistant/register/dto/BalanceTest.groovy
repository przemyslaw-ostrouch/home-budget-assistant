package com.przemyslawostrouch.homebudgetassistant.register.dto

import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import spock.lang.Specification

class BalanceTest extends Specification {

    def 'should add recharge current account'() {
        given:
        Balance balance = new Balance(BigDecimal.valueOf(1000))
        Register register = new Register(1L, "anyName", balance)

        when:
        register.recharge(BigDecimal.valueOf(2000))

        then:
        balance.value == 3000
    }
}
