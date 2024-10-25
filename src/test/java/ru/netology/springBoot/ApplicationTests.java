package ru.netology.springBoot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp(){
        myDevApp.start();
        myProdApp.start();

    }

    @Test
    void contextLoads() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" +
                myProdApp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production\n", entity.getBody());
        System.out.println(entity.getBody());

    }
    @Test
    void contextLoads2() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" +
                myDevApp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is dev\n", entity.getBody());
        System.out.println(entity.getBody());

    }}