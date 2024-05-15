// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.adapter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.application.service.SendMessageService;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.inbound.SendMessagePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SendMessageAdapter implements SendMessagePort {
    @Value("${parola.user-name:Anonymous}")
    private String nickName;

    @NonNull
    private SendMessageService sendMessageService;

    @Override
    public void send(@NonNull String message) {
        sendMessageService.sendMessage(new Message(nickName, message, System.currentTimeMillis()));
    }
}
