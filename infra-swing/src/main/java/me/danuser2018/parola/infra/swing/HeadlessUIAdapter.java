// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.outbound.MessageReceivedPort;
import me.danuser2018.parola.domain.port.outbound.StartUIPort;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Slf4j
@Repository
@Conditional(HeadlessUIAdapter.Condition.class)
public class HeadlessUIAdapter implements StartUIPort, MessageReceivedPort {

    public static class Condition implements org.springframework.context.annotation.Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return GraphicsEnvironment.isHeadless();
        }
    }

    @Override
    public void startUI() {
        log.warn("Headless mode is activated. Cannot display user interface !!!");
        log.warn("Consider add '-Djava.awt.headless=false' to your execution command to disable headless mode");
    }

    @Override
    public void messageReceived(@NonNull Message message) {
        // Do nothing
    }
}
