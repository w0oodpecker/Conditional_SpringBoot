package org.example.conditional_springboot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);

        //assert
        String expectationDevResponse = "Current profile is dev";
        String expectationProdResponse = "Current profile is production";

        //act
        Assertions.assertEquals(expectationDevResponse, devEntity.getBody());
        Assertions.assertEquals(expectationProdResponse, prodEntity.getBody());
    }

    @AfterAll
    public static void setDown(){
        devapp.stop();
        prodapp.stop();
    }
}