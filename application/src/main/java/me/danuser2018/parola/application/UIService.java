// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.domain.port.outbound.StartUIPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UIService {

    @NonNull
    private final StartUIPort startUIPort;

    public void startUI() {
        startUIPort.startUI();
    }
}
