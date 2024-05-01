// SPDX-License-Identifier: MIT

package me.danuser2018.parola;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ParolaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParolaApplication.class, args);
    }
}
