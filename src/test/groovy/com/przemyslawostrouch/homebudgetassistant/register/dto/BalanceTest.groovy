package com.przemyslawostrouch.homebudgetassistant.register.dto

import spock.lang.Specification

class BalanceTest extends Specification {

    def 'should add recharge current account'() {
        given:
        Balance balance = Balance.builder()
                .currency(MoneyValue.Currency.PLN)
                .value(BigDecimal.valueOf(1000))
                .build()

        when:
        balance.recharge(BigDecimal.valueOf(2000))

        then:
        balance.value == 3000
    }
}
