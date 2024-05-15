// SPDX-License-Identifier: MIT

// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.domain.port.outbound.StartUIPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UIService {

    private StartUIPort startUIPort;

    @Autowired
    public void setStartUIPort(@Lazy @NonNull final StartUIPort startUIPort) {
        this.startUIPort = startUIPort;
    }

    public void startUI() {
        startUIPort.startUI();
    }
}
