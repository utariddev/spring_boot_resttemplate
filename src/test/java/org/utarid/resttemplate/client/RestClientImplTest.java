package org.utarid.resttemplate.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestClientImplTest {

    @Autowired
    RestClientImpl restClient;

    @Test
    void listUsers() {
        restClient.listUsersEntity();
    }

    @Test
    void listUsersObject() {
        restClient.listUsersObject();
    }

    @Test
    void createUser() {
        restClient.createUser();
    }

    @Test
    public void exchange() {
        restClient.exchange();
    }

    @Test
    public void execute() {
        restClient.execute();
    }
}