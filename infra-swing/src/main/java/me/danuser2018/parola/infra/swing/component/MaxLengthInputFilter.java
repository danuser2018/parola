// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import me.danuser2018.parola.infra.swing.NoHeadlessUIAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@Component
@ConditionalOnBean(NoHeadlessUIAdapter.class)
public class MaxLengthInputFilter extends PlainDocument {

    private final int maxLength;

    public MaxLengthInputFilter(
            @Value("${user-interface.messages.max-length:255}") int maxLength
    ) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
        if (str == null) {
            return;
        }

        if (getLength() + str.length() <= maxLength) {
            super.insertString(offset, str, attr);
        }
    }
}
