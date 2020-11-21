package com.przemyslawostrouch.homebudgetassistant.register.controller


import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import org.apache.http.HttpStatus

class RegisterControllerTest extends HomeBudgetRestClient {

    def 'should get all registers with current balance'() {
        when:
        def result = get(Register[].class, '/registers')

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
    }

    def 'should recharge register'() {
        when:
        def result = put("/registers/$id", transferValue, Register.class)

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
        result.body.id == expectedId
        result.body.name == expectedName
        result.body.balance.value == expectedBalance

        where:
        transferValue                              | id || expectedId || expectedName || expectedBalance || expectedStatus
        new TransferValue(BigDecimal.valueOf(1))   | 1  || 1L         || 'Wallet'     || 1001.00         || HttpStatus.SC_OK
        new TransferValue(BigDecimal.valueOf(200)) | 2  || 2L         || 'Savings'    || 5200.00         || HttpStatus.SC_OK
    }

    def 'should fail when recharge value is lower or equal 0'() {
        when:
        def result = put("/registers/$id", transferValue, Exception.class)

        then:
        result.getStatusCode().value() == expectedStatus

        where:
        transferValue                             | id                || expectedStatus
        new TransferValue(BigDecimal.valueOf(0))  | 1                 || HttpStatus.SC_INTERNAL_SERVER_ERROR
        new TransferValue(BigDecimal.valueOf(-1)) | 2                 || HttpStatus.SC_INTERNAL_SERVER_ERROR
        new TransferValue(BigDecimal.valueOf(10)) | Integer.MAX_VALUE || HttpStatus.SC_NOT_FOUND
    }
}
