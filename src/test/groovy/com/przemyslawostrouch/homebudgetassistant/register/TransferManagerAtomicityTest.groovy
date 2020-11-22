package com.przemyslawostrouch.homebudgetassistant.register

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest
class TransferManagerAtomicityTest extends Specification {

    @Autowired
    RegisterRepository registerRepository

    @Transactional
    def 'should not commit transaction and throw runtime exception'() {

        given:
        RegisterTransactionManager mockedTransactionManager = Stub(RegisterTransactionManager.class)

        mockedTransactionManager.saveTransactionBetweenAccounts(_ as TransferValue, _ as Register, _ as Register) >> {
            throw new RuntimeException()
        }
        TransferManager transferManager = new TransferManager(registerRepository, new RegisterFinder(registerRepository), mockedTransactionManager)
        TransferRequest transferRequest = TransferRequest.builder()
                .fromRegisterId(1L)
                .toRegisterId(4L)
                .transfer(new TransferValue(BigDecimal.valueOf(10)))
                .build()
        when:
        transferManager.transfer(transferRequest)

        then:
        thrown(RuntimeException)
    }

    def 'After fail during saving transaction full transaction should be roll backed'() {
        when:
        def firstBalance = registerRepository.findById(1L).get().balanceValue
        def secondBalance = registerRepository.findById(4L).get().balanceValue

        then:
        firstBalance == 1000
        secondBalance == 0
    }
}
