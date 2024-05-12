// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.danuser2018.parola.domain.port.outbound.StartUIPort;
import me.danuser2018.parola.infra.swing.component.MainJFrame;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Repository
@Conditional(NoHeadlessUIAdapter.Condition.class)
@RequiredArgsConstructor
public class NoHeadlessUIAdapter implements StartUIPort {

    public static class Condition implements org.springframework.context.annotation.Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return !GraphicsEnvironment.isHeadless();
        }
    }

    @NonNull
    private final MainJFrame mainJFrame;

    @Override
    public void startUI() {
        SwingUtilities.invokeLater(() -> mainJFrame.setVisible(true));
    }
}
