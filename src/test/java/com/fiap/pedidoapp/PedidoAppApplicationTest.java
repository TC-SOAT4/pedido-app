package com.fiap.pedidoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PedidoAppApplicationTest {

//    @Test
    public void test() {
        String[] args = new String[] {};
        SpringApplication.run(PedidoAppApplication.class, args);
    }

}