package com.project.map.service;

import org.apache.xmlgraphics.image.loader.spi.ImageConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class CreateImage {

    public static BufferedImage createImage(String imagePath) throws IOException {

        InputStream inputStream = ImageConverter.class.getResourceAsStream(imagePath);

        if (inputStream == null) {
            throw new IOException("Image not found in resources folder.");
        }

        // Step 2: Convert the InputStream to a BufferedImage
        BufferedImage bufferedImage = ImageIO.read(inputStream);

        // Close the input stream (important)
        inputStream.close();

        return bufferedImage;
    }

    public static BufferedImage addTextToImage(int width, int height, String text, int fontSize)
            throws IOException {

        // Load the existing image
        File imageFile = new File("src/main/resources/static/bg.png");
        BufferedImage image = ImageIO.read(imageFile);

        // Create a graphics context from the image
        Graphics2D graphics = image.createGraphics();

        // Set font properties
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setFont(font);
        graphics.setColor(Color.decode("#858FB0"));

        // Calculate text position to center it on the image
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();
        int x = ((width - textWidth) / 2) + 17;
        int y = ((height + textHeight) / 2);

        // Draw the text on the image
        graphics.drawString(text, x, y);

        // Dispose the graphics context
        graphics.dispose();

        return image;
    }

}
