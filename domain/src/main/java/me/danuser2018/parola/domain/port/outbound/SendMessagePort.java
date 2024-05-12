// SPDX-License-Identifier: MIT

package me.danuser2018.parola.domain.port.outbound;

import lombok.NonNull;
import me.danuser2018.parola.domain.model.Message;

public interface SendMessagePort {
    void sendMessage(@NonNull Message message);
}
