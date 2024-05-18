package com.fiap.pedidoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PedidoAppApplicationTest {

    //@Test
    public void test() {
        String[] args = new String[] {};
        SpringApplication.run(PedidoAppApplication.class, args);
    }

}