// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import lombok.Getter;
import lombok.Value;
import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@ConditionalOnBean(NoHeadlessUIAdapter.class)
@Getter
public class MessagesPanel extends JPanel {

    private final JScrollPane scrollPane;

    public MessagesPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(this);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

}
