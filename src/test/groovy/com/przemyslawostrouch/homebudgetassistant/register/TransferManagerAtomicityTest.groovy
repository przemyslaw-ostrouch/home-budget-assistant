package com.przemyslawostrouch.homebudgetassistant.register

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction
import com.przemyslawostrouch.homebudgetassistant.register.repository.RegisterRepository
import com.przemyslawostrouch.homebudgetassistant.register.repository.TransactionRepository
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
    @Autowired
    RegisterFinder registerFinder

    @Transactional
    def 'should not commit transaction and throw runtimeExcaption'() {

        given:
        TransactionRepository mockedTransactionRepo = Stub(TransactionRepository.class)

        mockedTransactionRepo.save(_ as Transaction) >> {
            throw new RuntimeException()
        }

        TransferManager transferManager = new TransferManager(registerRepository, mockedTransactionRepo, registerFinder)
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

    def 'After fail during saving transaction full transaction should be roll backed'(){
        when:
        def firstBalance = registerRepository.findById(1L).get().balanceValue
        def secondBalance = registerRepository.findById(4L).get().balanceValue

        then:
        firstBalance == 1000
        secondBalance == 0
    }
}
