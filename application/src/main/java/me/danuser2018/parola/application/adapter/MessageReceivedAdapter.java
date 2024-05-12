// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.adapter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.application.service.MessageReceivedService;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.inbound.MessageReceivedPort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageReceivedAdapter implements MessageReceivedPort {

    @NonNull
    private MessageReceivedService messageReceivedService;

    @Override
    public void messageReceived(@NonNull Message message) {
        messageReceivedService.messageReceived(message);
    }
}
