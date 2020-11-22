package com.przemyslawostrouch.homebudgetassistant.register.entity

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectRegisterBalanceException
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import spock.lang.Specification

class RegisterTest extends Specification {

    Register register

    def setup() {
        register = Register.builder()
                .name("any name")
                .balance(new Balance(1000))
                .build()
    }

    def "should throw exception when not enough money on account"() {
        given:
        TransferValue transferValue = new TransferValue(10000)

        when:
        register.validateBalance(transferValue)

        then:
        thrown(IncorrectRegisterBalanceException)
    }

    def "should reduce account balance"() {
        given:
        TransferValue transferValue = new TransferValue(100)

        when:
        Register afterOperation = register.reduceBalanceValue(transferValue)

        then:
        afterOperation.balance.value == 900
    }

    def "should increase account balance"() {
        given:
        TransferValue transferValue = new TransferValue(200)

        when:
        Register afterOperation = register.recharge(transferValue)

        then:
        afterOperation.balance.value == 1200
    }
}
