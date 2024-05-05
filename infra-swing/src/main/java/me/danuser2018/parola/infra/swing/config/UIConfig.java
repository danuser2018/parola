// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.config;

import com.formdev.flatlaf.FlatDarkLaf;
import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

@Configuration
@ConditionalOnBean(NoHeadlessUIAdapter.class)
public class UIConfig {

    private static final String BASE_PATH = "infra-swing/src/main/resources/";

    public UIConfig() {
        FlatDarkLaf.setup();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        final var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    @Bean
    public ImageIcon logoIcon() {
        return new ImageIcon(BASE_PATH + "main.png");
    }

    @Bean
    public ImageIcon sendIcon() { return new ImageIcon(BASE_PATH + "send.png"); }

    @Bean
    public String messagesTemplate() throws IOException {
        return new String(Files.readAllBytes(Paths.get(BASE_PATH + "label.html")));
    }
}
