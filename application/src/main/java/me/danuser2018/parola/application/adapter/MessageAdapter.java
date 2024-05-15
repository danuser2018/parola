// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.adapter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.application.service.MessageService;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.inbound.MessageReceivedPort;
import me.danuser2018.parola.domain.port.inbound.SendMessagePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageAdapter implements MessageReceivedPort, SendMessagePort {
    @Value("${parola.user-name:Anonymous}")
    private String nickName;

    @NonNull
    private final MessageService messageService;

    @Override
    public void messageReceived(@NonNull Message message) {
        messageService.messageReceived(message);
    }

    @Override
    public void send(@NonNull String message) {
        messageService.sendMessage(new Message(nickName, message, System.currentTimeMillis()));
    }
}
