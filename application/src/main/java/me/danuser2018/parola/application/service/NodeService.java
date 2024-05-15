// SPDX-License-Identifier: MIT

// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.service;

import lombok.NonNull;
import me.danuser2018.parola.domain.port.outbound.StartNodePort;
import me.danuser2018.parola.domain.port.outbound.StopNodePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class NodeService {

    private StartNodePort startNodePort;

    private StopNodePort stopNodePort;

    @Autowired
    public void setStartNodePort(@Lazy @NonNull final StartNodePort startNodePort) {
        this.startNodePort = startNodePort;
    }

    @Autowired
    public void setStopNodePort(@Lazy @NonNull final StopNodePort stopNodePort) {
        this.stopNodePort = stopNodePort;
    }

    public void startNode() {
        startNodePort.startNode();
    }

    public void stopNode() {
        stopNodePort.stopNode();
    }
}
