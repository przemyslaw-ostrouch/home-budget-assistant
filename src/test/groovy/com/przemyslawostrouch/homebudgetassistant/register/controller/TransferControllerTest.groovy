package com.przemyslawostrouch.homebudgetassistant.register.controller

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectTransferValueException
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction
import com.przemyslawostrouch.homebudgetassistant.register.RegisterRepository
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class TransferControllerTest extends HomeBudgetRestClient {

    @Autowired
    RegisterRepository registerRepository

    @Unroll
    def 'should transfer money between registers'() {
        when:
        def transferRequest = newTransferRequest(fromId, toId, transferValue)
        def result = post("/transfers", transferRequest, Transaction.class)

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
        result.body.fromRegister.name == fromRegisterName
        result.body.fromRegister.balance.value == fromExpectedBalanceAfter
        result.body.toRegister.name == toRegisterName
        result.body.toRegister.balance.value == toExpectedBalaceAfter
        result.body.transactionDateTime != null

        cleanup:
        post("/transfers", newTransferRequest(toId, fromId, transferValue), Transaction.class)

        where:
        fromId | toId | transferValue || fromRegisterName || toRegisterName     || fromExpectedBalanceAfter || toExpectedBalaceAfter
        1L     | 4L   | 0.01          || 'Wallet'         || 'Food expenses'    || 999.99                   || 0.01
        1L     | 4L   | 999.99        || 'Wallet'         || 'Food expenses'    || 0.01                     || 999.99
        2L     | 3L   | 5000          || 'Savings'        || 'Insurance policy' || 0                        || 5000
        2L     | 3L   | 10.9          || 'Savings'        || 'Insurance policy' || 4989.1                   || 10.9
    }

    @Unroll
    def 'should fail during money transfer'() {
        when:
        def result = post("/transfers", transferRequest, IncorrectTransferValueException.class)
        print(result.body)

        then:
        result.getStatusCode().value() == expectedStatus

        where:
        transferRequest         || expectedStatus
        negativeTransferValue() || HttpStatus.SC_INTERNAL_SERVER_ERROR
    }

    static def newTransferRequest(fromId, toId, value) {
        TransferRequest.builder()
                .fromRegisterId(fromId)
                .toRegisterId(toId)
                .transfer(new TransferValue(BigDecimal.valueOf(value)))
                .build()
    }

    static def negativeTransferValue() {
        TransferRequest.builder()
                .fromRegisterId(3L)
                .toRegisterId(2L)
                .transfer(new TransferValue(BigDecimal.valueOf(-10)))
                .build()
    }
}
