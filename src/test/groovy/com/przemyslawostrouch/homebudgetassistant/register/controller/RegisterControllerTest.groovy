package com.przemyslawostrouch.homebudgetassistant.register.controller

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import org.apache.http.HttpStatus

class RegisterControllerTest extends HomeBudgetRestClient {

    def 'should get register with current balance'() {
        when:
        def result = get(Register.class, '/registers/1')

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
        result.body.id == 1L
        result.body.name == 'Wallet'
        result.body.balance.value == 1000.00
    }

    def 'should recharge register'() {
        given:
        TransferValue transferValue = new TransferValue(new BigDecimal(1))

        when:
        def result = put('/registers/1', transferValue, Register.class)

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
        result.body.id == 1L
        result.body.name == 'Wallet'
        result.body.balance.value == 1001.00
    }
}
