// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import lombok.NonNull;
import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

@Component
@ConditionalOnBean(NoHeadlessUIAdapter.class)
public class MainJFrame extends JFrame {

    private final static String TITLE_KEY = "mainFrame.title";

    public MainJFrame(
            @Value("${user-interface.main-frame.width:800}") final int width,
            @Value("${user-interface.main-frame.height:600}") final int height,
            @NonNull final MessageSource messageSource,
            @NonNull final ImageIcon logoIcon,
            @NonNull final MessagesPanel messagesPanel,
            @NonNull final InputPanel inputPanel
    ) {
        super(messageSource.getMessage(TITLE_KEY, null, Locale.getDefault()));

        setIconImage(logoIcon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        add(messagesPanel.getScrollPane(), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
}
