// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.config;

import com.formdev.flatlaf.FlatDarkLaf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.swing.*;
import java.util.Locale;

@Configuration
public class UIConfig {

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
    public Locale locale() {
        return Locale.getDefault();
    }

    @Bean
    public ImageIcon mainIcon() {
        return new ImageIcon("infra-swing/src/main/resources/main.png");
    }
}
