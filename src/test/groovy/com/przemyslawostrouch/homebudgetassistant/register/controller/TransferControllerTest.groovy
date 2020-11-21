package com.przemyslawostrouch.homebudgetassistant.register.controller

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectTransferValueException
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction
import org.apache.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class TransferControllerTest extends HomeBudgetRestClient {

    @Unroll
    def 'should transfer money between registers'() {
        when:
        def result = post("/transfers", transferRequest, Transaction.class)
        print(result.body)

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
        result.body.fromRegister.name == fromRegisterName
        result.body.fromRegister.balance.value == fromExpectedBalanceAfter
        result.body.toRegister.name == toRegisterName
        result.body.toRegister.balance.value == toExpectedBalaceAfter
        result.body.transactionDateTime != null

        where:
        transferRequest        || fromRegisterName   || toRegisterName     || fromExpectedBalanceAfter || toExpectedBalaceAfter
        smallAmountOfMoney()   || 'Wallet'           || 'Food expenses'    || 999.999999999            || 0.000000001
        restMoneyTransfer()    || 'Wallet'           || 'Food expenses'    || 0                        || 1000
        fullMoneyTransfer()    || 'Savings'          || 'Insurance policy' || 0                        || 5000
        decimalMoneyTransfer() || 'Insurance policy' || 'Savings'          || 4989.1                   || 10.9
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

    def smallAmountOfMoney() {
        TransferRequest.builder()
                .fromRegisterId(1L)
                .toRegisterId(4L)
                .transfer(new TransferValue(BigDecimal.valueOf(0.000000001)))
                .build()
    }

    def restMoneyTransfer() {
        TransferRequest.builder()
                .fromRegisterId(1L)
                .toRegisterId(4L)
                .transfer(new TransferValue(BigDecimal.valueOf(999.999999999)))
                .build()
    }

    def fullMoneyTransfer() {
        TransferRequest.builder()
                .fromRegisterId(2L)
                .toRegisterId(3L)
                .transfer(new TransferValue(BigDecimal.valueOf(5000)))
                .build()
    }

    def decimalMoneyTransfer() {
        TransferRequest.builder()
                .fromRegisterId(3L)
                .toRegisterId(2L)
                .transfer(new TransferValue(BigDecimal.valueOf(10.9)))
                .build()
    }

    def negativeTransferValue() {
        TransferRequest.builder()
                .fromRegisterId(3L)
                .toRegisterId(2L)
                .transfer(new TransferValue(BigDecimal.valueOf(-10)))
                .build()
    }
}
