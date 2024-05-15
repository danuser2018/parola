// SPDX-License-Identifier: MIT

package me.danuser2018.parola.infra.swing.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Identicon {
    private static final int ORIGINAL_SIZE = 200;

    private final byte[] hash;
    private final ImageIcon identicon;

    public Identicon(String text) {
        this.hash = getSHA256Hash(text);
        this.identicon = generateIdenticon();
    }

    public ImageIcon getImageIcon(final int size) {
        if (size == ORIGINAL_SIZE) {
            return this.identicon;
        } else {
            return getResizedImageIcon(size);
        }
    }

    private byte[] getSHA256Hash(final String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException ignore) {
            return new byte[0];
        }
    }

    private ImageIcon generateIdenticon() {
        BufferedImage image = new BufferedImage(ORIGINAL_SIZE, ORIGINAL_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        int shapeSize = ORIGINAL_SIZE / 8;

        Color bgColor = color(0);
        Color fgColor = color(1);

        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, ORIGINAL_SIZE, ORIGINAL_SIZE);

        graphics.setColor(fgColor);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 8; y++) {
                int index = y * 4 + x;
                if (isVisible(index)) {
                    int xPos = x * shapeSize;
                    int mirrorXPos = ORIGINAL_SIZE - xPos - shapeSize;
                    int yPos = y * shapeSize;
                    graphics.fillRect(xPos, yPos, shapeSize, shapeSize);
                    graphics.fillRect(mirrorXPos, yPos, shapeSize, shapeSize);
                }
            }
        }

        graphics.dispose();
        return new ImageIcon(image);
    }

    private Color color(int index) {
        return new Color(red(index), green(index), blue(index));
    }

    private int red(final int index) {
        return hash[index] & 0xff;
    }

    private int green(final int index) {
        return hash[(index + 1) % hash.length] & 0xff;
    }

    private int blue(final int index) {
        return hash[(index + 2) % hash.length] & 0xff;
    }

    private Boolean isVisible(final int index) {
        return (hash[index] & 1) == 1;
    }

    private ImageIcon getResizedImageIcon(int size) {
        Image image = this.identicon.getImage();
        Image resizedImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}