package com.przemyslawostrouch.homebudgetassistant.register.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeBudgetRestClient extends Specification {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate restTemplate
    @Shared
    def host = 'http://localhost:'

    ResponseEntity get(Class classType, String url) {
        return restTemplate.getForEntity(host + port + url, classType)
    }

    ResponseEntity post(String url, def body, Class responseClassType) {
        return restTemplate.exchange(host + port + url, HttpMethod.POST, new HttpEntity<Object>(body), responseClassType)
    }

}
