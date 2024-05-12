// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.outbound.MessageReceivedPort;
import me.danuser2018.parola.infra.swing.component.MessageHandler;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

@Repository
@Conditional(NoHeadlessUIAdapter.Condition.class)
@RequiredArgsConstructor
public class MessageReceivedUIAdapter implements MessageReceivedPort {

    @NonNull
    private MessageHandler messageHandler;

    @Override
    public void messageReceived(@NonNull Message message) {
        messageHandler.handleMessage(message.getNickName(), message.getMessage(), message.getTimestamp());
    }
}
