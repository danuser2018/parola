// SPDX-License-Identifier: MIT

package me.danuser2018.parola;

import lombok.extern.slf4j.Slf4j;
import me.danuser2018.parola.application.service.NodeService;
import me.danuser2018.parola.application.service.UIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ParolaApplication {

    public static void main(final String[] args) {

        final var context = SpringApplication.run(ParolaApplication.class, args);

        log.info("Starting user interface ...");
        final var uiService = context.getBean(UIService.class);
        uiService.startUI();

        log.info("Starting node ...");
        final var nodeService = context.getBean(NodeService.class);
        nodeService.startNode();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            nodeService.stopNode();
            log.info("Parola app. finished");
        }));
    }
}
