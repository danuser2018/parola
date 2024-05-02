// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import lombok.NonNull;
import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Locale;

@Component
@ConditionalOnBean(NoHeadlessUIAdapter.class)
public class MainFrame extends JFrame {

    private final static String TITLE_KEY = "mainFrame.title";

    public MainFrame(
            @NonNull final MessageSource messageSource,
            @NonNull final Locale locale,
            @Value("${user-interface.main-frame.width:800}") final int width,
            @Value("${user-interface.main-frame.height:600}") final int height,
            @NonNull final ImageIcon mainIcon
    ) {
        super(messageSource.getMessage(TITLE_KEY, null, locale));
        setIconImage(mainIcon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        setLocationRelativeTo(null);
    }
}
