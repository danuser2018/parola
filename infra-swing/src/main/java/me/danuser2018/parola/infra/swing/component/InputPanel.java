// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import lombok.NonNull;
import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Component
@ConditionalOnBean(NoHeadlessUIAdapter.class)
public class InputPanel extends JPanel {

    public InputPanel(
            @NonNull MaxLengthInputFilter maxLengthInputFilter,
            @NonNull ImageIcon sendIcon,
            @NonNull MessageHandler messageHandler
    ) {
        super(new GridBagLayout());
        setBorder(new EmptyBorder(10, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();

        var messageField = new JTextField();
        messageField.setDocument(maxLengthInputFilter);
        messageField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        messageField.setFont(messageField.getFont().deriveFont(14f));
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(messageField, gbc);

        JButton sendButton = new JButton();
        sendButton.setIcon(sendIcon);
        sendButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 5));
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        add(sendButton, gbc);

        messageField.addActionListener(event -> sendButton.doClick());
        sendButton.addActionListener(event -> {
            final var message = messageField.getText();
            if (message !=null && !message.trim().isEmpty()) {
                messageHandler.handleMessage("Anonymous", message, System.currentTimeMillis());
            }
            messageField.setText("");
            messageField.requestFocus();
        });
    }
}
