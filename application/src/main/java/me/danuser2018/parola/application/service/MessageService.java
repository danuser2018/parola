// SPDX-License-Identifier: MIT

package me.danuser2018.parola.application.service;

import lombok.NonNull;
import me.danuser2018.parola.domain.model.Message;
import me.danuser2018.parola.domain.port.outbound.IsMessageStoredPort;
import me.danuser2018.parola.domain.port.outbound.MessageReceivedPort;
import me.danuser2018.parola.domain.port.outbound.SendMessagePort;
import me.danuser2018.parola.domain.port.outbound.StoreMessagePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class MessageService {

    private MessageReceivedPort messageReceivedPort;
    private SendMessagePort sendMessagePort;
    private IsMessageStoredPort isMessageStoredPort;
    private StoreMessagePort storeMessagePort;

    @Autowired
    public void setMessageReceivedPort(@Lazy final @NonNull MessageReceivedPort messageReceivedPort) {
        this.messageReceivedPort = messageReceivedPort;
    }

    @Autowired
    public void setSendMessagePort(@Lazy final @NonNull SendMessagePort sendMessagePort) {
        this.sendMessagePort = sendMessagePort;
    }

    @Autowired
    public void setIsMessageStoredPort(@Lazy final @NonNull IsMessageStoredPort isMessageStoredPort) {
        this.isMessageStoredPort = isMessageStoredPort;
    }

    @Autowired
    public void setStoreMessagePort(@Lazy final @NonNull StoreMessagePort storeMessagePort) {
        this.storeMessagePort = storeMessagePort;
    }

    public void sendMessage(final @NonNull Message message) {
        sendMessagePort.sendMessage(message);
    }

    public synchronized void messageReceived(final @NonNull Message message) {
        Optional<String> hash = calculateHash(message);

        if (hash.isPresent() && !isMessageStoredPort.isStored(hash.get())) {
            storeMessagePort.store(hash.get());
            sendMessage(message);

            messageReceivedPort.messageReceived(message);
        }
    }

    private Optional<String> calculateHash(final Message message) {

        StringBuilder sb = new StringBuilder();
        sb.append(message.getNickName());
        sb.append(message.getMessage());
        sb.append(message.getTimestamp());

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calcular el hash del mensaje
            byte[] hashBytes = digest.digest(sb.toString().getBytes());

            // Convertir el hash a una representación hexadecimal
            return Optional.of(bytesToHex(hashBytes));
        } catch (NoSuchAlgorithmException ignored) {
            return Optional.empty();
        }
    }

    private String bytesToHex(final byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
