// SPDX-License-Identifier: MIT

// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.danuser2018.parola.domain.port.outbound.StartNodePort;
import me.danuser2018.parola.domain.port.outbound.StopNodePort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeService {

    @NonNull
    private final StartNodePort startNodePort;

    @NonNull
    private final StopNodePort stopNodePort;

    public void startNode() {
       startNodePort.startNode();
    }

    public void stopNode() {
        stopNodePort.stopNode();
    }
}
