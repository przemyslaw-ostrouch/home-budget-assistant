package com.przemyslawostrouch.homebudgetassistant.register.controller


import com.przemyslawostrouch.homebudgetassistant.register.entity.Register
import org.apache.http.HttpStatus

class RegisterControllerTest extends HomeBudgetRestClient {

    def 'should get all registers with current balance'() {
        when:
        def result = get(Register[].class, '/registers')

        then:
        result.getStatusCode().value() == HttpStatus.SC_OK
    }
}
