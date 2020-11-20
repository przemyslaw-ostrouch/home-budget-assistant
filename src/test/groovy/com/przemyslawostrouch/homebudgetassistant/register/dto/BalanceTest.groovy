package com.przemyslawostrouch.homebudgetassistant.register.dto

import spock.lang.Specification

class BalanceTest extends Specification {

    def 'should add recharge current account'() {
        given:
        Balance balance = new Balance(BigDecimal.valueOf(1000))

        when:
        balance.recharge(BigDecimal.valueOf(2000))

        then:
        balance.value == 3000
    }
}
