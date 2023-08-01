package com.project.map.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class CreateSnapshot {

    public static BufferedImage decodeBase64ToImage(String base64) {
        String checkSnapshot = checkText(base64);
        byte[] decodedBytes = Base64.getDecoder().decode(checkSnapshot);
        try {
            return ImageIO.read(new ByteArrayInputStream(decodedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String checkText(String base64) {
        String marker = "base64,";
        String result = "";
        int index = base64.indexOf(marker);
        if (index != -1) {
            result = base64.substring(index + marker.length());
        } else {
            result = base64;
        }
        return result;
    }

}
