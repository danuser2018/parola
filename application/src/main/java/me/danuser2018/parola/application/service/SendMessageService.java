// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.outbound.SendMessagePort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMessageService {

    @NonNull
    private SendMessagePort sendMessagePort;

    public void sendMessage(@NonNull Message message) {
        sendMessagePort.sendMessage(message);
    }
}
