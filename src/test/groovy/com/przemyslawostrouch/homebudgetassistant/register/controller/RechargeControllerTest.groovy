package com.przemyslawostrouch.homebudgetassistant.register.controller

import com.przemyslawostrouch.homebudgetassistant.register.dto.RechargeRequest
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction
import org.apache.http.HttpStatus

class RechargeControllerTest extends HomeBudgetRestClient {

    def 'should recharge register'() {
        when:
        def result = post("/recharges", rechargeRequest, Transaction.class)

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
        result.body.toRegister.name == expectedName
        result.body.toRegister.balance.value == expectedBalance

        where:
        rechargeRequest                                                     || expectedName || expectedBalance || expectedStatus
        new RechargeRequest(1L, new TransferValue(BigDecimal.valueOf(1)))   || 'Wallet'     || 1001.00         || HttpStatus.SC_OK
        new RechargeRequest(2L, new TransferValue(BigDecimal.valueOf(200))) || 'Savings'    || 5200.00         || HttpStatus.SC_OK
    }

    def 'should fail when recharge value is lower or equal 0'() {
        when:
        def result = post("/recharges", rechargeRequest, Exception.class)

        then:
        result.getStatusCode().value() == expectedStatus

        where:
        rechargeRequest                                                                   | id                || expectedStatus
        new RechargeRequest(1L, new TransferValue(BigDecimal.valueOf(0)))                 | 1                 || HttpStatus.SC_INTERNAL_SERVER_ERROR
        new RechargeRequest(2L, new TransferValue(BigDecimal.valueOf(-1)))                | 2                 || HttpStatus.SC_INTERNAL_SERVER_ERROR
        new RechargeRequest(Integer.MAX_VALUE, new TransferValue(BigDecimal.valueOf(10))) | Integer.MAX_VALUE || HttpStatus.SC_NOT_FOUND
    }
}
