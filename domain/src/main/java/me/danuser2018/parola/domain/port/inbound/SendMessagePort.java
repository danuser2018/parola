// SPDX-License-Identifier: MIT

package me.danuser2018.parola.domain.port.inbound;

import lombok.NonNull;
import me.danuser2018.parola.domain.model.Message;

public interface SendMessagePort {
    void send(@NonNull Message message);
}
