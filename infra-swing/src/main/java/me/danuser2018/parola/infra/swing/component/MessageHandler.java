// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@ConditionalOnBean(NoHeadlessUIAdapter.class)
@RequiredArgsConstructor
public class MessageHandler {

    @NonNull
    private final SimpleDateFormat dateFormat;

    @NonNull
    private final MessagesPanel messagesPanel;

    @NonNull
    private final String messagesTemplate;

    public void handleMessage(@NonNull String nickname, @NonNull String messageText, long timestamp) {

        Date date = new Date(timestamp);
        String dateStr = dateFormat.format(date);

        String message = String.format(messagesTemplate.trim(), nickname, dateStr, messageText);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        messagesPanel.add(messageLabel);

        messagesPanel.revalidate();
        messagesPanel.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = messagesPanel.getScrollPane().getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
}
